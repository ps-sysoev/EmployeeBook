package repository.impl;

import lombok.extern.slf4j.Slf4j;
import model.Employee;
import repository.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class EmployeeDatabaseRepository implements Repository<Long, Employee> {
    Connection connection;

    @Override
    public void init() {
        try {
            File databaseProperties = new File("src/main/resources/database.properties");
            Properties config = new Properties();
            config.load(new FileReader(databaseProperties));

            try {
                connection = DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password"));

                DatabaseMetaData databaseMetaData = connection.getMetaData();

                log.info("Connection to database is successful {}", !databaseMetaData.getConnection().isClosed());
                log.info("Connection to database by url {}", databaseMetaData.getURL());

                String script = Files.readString(Paths.get("src/main/resources/scripts/employees_table.sql"),
                        StandardCharsets.UTF_8);

                try (Statement statement = connection.createStatement()) {
                    statement.execute(script);
                }

            } catch (IOException e) {
                log.error("Connection error in the init method {}, {}", e.getClass().getCanonicalName(), e.getMessage());

                e.printStackTrace();
            }

            Class.forName(config.getProperty("driver"));
        } catch (Exception e) {
            log.error("Connection error in the init method {}, {}", e.getClass().getCanonicalName(), e.getMessage());
        }
    }

    @Override
    public Employee save(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO employees(name, country, created) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getCountry());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(employee.getCreated()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (Objects.nonNull(resultSet) && resultSet.next()) {
                long generatedId = resultSet.getLong("id");

                employee.setId(generatedId);

                log.info("Created new employee with id - {}", generatedId);
            }
        } catch (SQLException e) {
            log.error("SQLException in the save method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public boolean update(Long id, Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employees SET name = ?, country = ? WHERE id = ?")) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getCountry());
            preparedStatement.setLong(3, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException in the update method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM employees WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException in the delete method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employees WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .created(resultSet.getTimestamp("created").toLocalDateTime())
                        .build();

                return Optional.ofNullable(employee);
            }
        } catch (SQLException e) {
            log.error("SQLException in the findById method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM employees");

            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .created(resultSet.getTimestamp("created").toLocalDateTime())
                        .build();

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            log.error("SQLException in the findAll method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employees WHERE name = ?")) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .created(resultSet.getTimestamp("created").toLocalDateTime())
                        .build();

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            log.error("SQLException in the findByName method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public List<Employee> findByCreatedDateInterval(LocalDateTime start, LocalDateTime end) {
        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employees WHERE created BETWEEN ? AND ?")) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .created(resultSet.getTimestamp("created").toLocalDateTime())
                        .build();

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            log.error("SQLException in the findByCreatedDateInterval method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }

        return employeeList;
    }
}
