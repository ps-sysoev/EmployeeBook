package repository;

import model.Employee;

import java.util.Arrays;
import java.util.Objects;

public class EmployeeRepository {
    private Employee[] employees = new Employee[100];
    private int index = 0;

    public Employee save(Employee employee) {
        if (index == employees.length - 1) {
            System.out.println("Book size is max.");
            employee.setId(-1);
        } else {
            employees[index] = employee;
            index++;
        }

        return employee;
    }

    public boolean update(long id, Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            long intermediateId = employees[i].getId();

            if (intermediateId == id) {
                employee.setId(intermediateId);
                employees[i] = employee;

                return true;
            }
        }

        return false;
    }

    public boolean delete(long id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                employees[i] = null;
                index--;

                for (int j = 0; j < employees.length - 1; j++) {
                    if (Objects.isNull(employees[j])) {
                        employees[j] = employees[j + 1];
                        employees[j + 1] = null;
                    }
                }

                return true;
            }
        }

        return false;
    }

    public boolean delete(String country) {
        return true;
    }

    public Employee findById(long id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }

    public Employee[] findAllByName(String name) {
        return null;
    }

    public Employee[] findAll() {
        int count = 0;
        Employee[] intermediateArrayOfEmployees = new Employee[employees.length];

        for (Employee employee : employees) {
            if (Objects.nonNull(employee)) {
                intermediateArrayOfEmployees[count++] = employee;
            }
        }

        return Arrays.copyOfRange(intermediateArrayOfEmployees, 0, count);
    }
}