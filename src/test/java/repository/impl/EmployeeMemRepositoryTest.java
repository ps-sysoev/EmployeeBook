package repository.impl;

import model.Employee;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeMemRepositoryTest {
    private final EmployeeMemRepository employeeMemRepository = new EmployeeMemRepository();

    @Test
    public void saveTest() {
        Employee employee = employeeMemRepository.save(new Employee("Vladimir", "Russia"));

        assertTrue(employeeMemRepository.findById(employee.getId()).isPresent());
    }

    @Test
    public void updateTest() {
        Employee employee = employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        boolean result = employeeMemRepository.update(employee.getId(), new Employee("VV", "Russia"));

        assertTrue(result);

        Employee checkEmployeeUpdated = employeeMemRepository.findById(employee.getId()).orElseThrow();

        assertEquals("Russia", checkEmployeeUpdated.getCountry());
        assertEquals("VV", checkEmployeeUpdated.getName());
    }

    @Test
    public void updateNotSuccessTest() {
        employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        boolean result = employeeMemRepository.update(5L, new Employee("VV", "Russia"));

        assertFalse(result);
    }

    @Test
    public void deleteTest() {
        employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        Employee employee = employeeMemRepository.save(new Employee("Dmitry", "Russia"));

        boolean result = employeeMemRepository.delete(employee.getId());

        assertTrue(result);
        assertEquals(1, employeeMemRepository.findAll().size());
    }

    @Test
    public void deleteNotSuccessTest() {
        employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        employeeMemRepository.save(new Employee("Dmitry", "Russia"));

        boolean result = employeeMemRepository.delete(5L);

        assertFalse(result);
        assertEquals(2, employeeMemRepository.findAll().size());

    }

    @Test
    public void findByName() {
        employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        employeeMemRepository.save(new Employee("Dmitry", "Russia"));
        employeeMemRepository.save(new Employee("Vladimir", "Germany"));

        List<Employee> employees = employeeMemRepository.findByName("Vladimir");

        assertEquals(2, employees.size());

        employees.forEach(employee -> assertEquals("Vladimir", employee.getName()));
    }

    @Test
    public void findByNameIsEmptyTest() {
        employeeMemRepository.save(new Employee("Vladimir", "Russia"));
        employeeMemRepository.save(new Employee("Dmitry", "Russia"));
        employeeMemRepository.save(new Employee("Vladimir", "Germany"));

        List<Employee> employees = employeeMemRepository.findByName("Petr");

        assertTrue(employees.isEmpty());
    }

    @Test
    public void findByCreatedDateIntervalTest() {
        // TODO
    }

    @Test
    public void indexOfTest() {
        // TODO
    }

    @Test
    public void generateIdTest() {
        // TODO
    }
}