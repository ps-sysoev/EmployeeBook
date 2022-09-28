package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import model.Employee;
import org.junit.Test;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;
import sort.Ordered;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortedByOrderActionTest {
    private final SortedByOrderAction sortedByOrderAction = new SortedByOrderAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenSortedByOrderAscTest() {
        Input input = Mockito.mock(ConsoleInput.class);

        Mockito.when(input.askStr("Enter order type (ASC or DESC): ")).thenReturn(String.valueOf(Ordered.ASC));

        List<Employee> employees = new ArrayList<>();

        Employee firstEmployee = new Employee("Alexander", "Belarus");
        employees.add(firstEmployee);

        Employee secondEmployee = new Employee("Vladimir", "Russia");
        employees.add(secondEmployee);

        Employee thirdEmployee = new Employee("Dmitry", "Russia");
        employees.add(thirdEmployee);

        Employee fourthEmployee = new Employee("Ivan", "Russia");
        employees.add(fourthEmployee);

        Mockito.when(employeeMemRepository.findAll()).thenReturn(employees);

        boolean result = sortedByOrderAction.execute(input, employeeService);

        assertTrue(result);
    }

    @Test
    public void whenSortedByOrderDescTest() {
        Input input = Mockito.mock(ConsoleInput.class);

        Mockito.when(input.askStr("Enter order type (ASC or DESC): ")).thenReturn(String.valueOf(Ordered.DESC));

        List<Employee> employees = new ArrayList<>();

        Employee firstEmployee = new Employee("Alexander", "Belarus");
        employees.add(firstEmployee);

        Employee secondEmployee = new Employee("Vladimir", "Russia");
        employees.add(secondEmployee);

        Employee thirdEmployee = new Employee("Dmitry", "Russia");
        employees.add(thirdEmployee);

        Employee fourthEmployee = new Employee("Ivan", "Russia");
        employees.add(fourthEmployee);

        Mockito.when(employeeMemRepository.findAll()).thenReturn(employees);

        boolean result = sortedByOrderAction.execute(input, employeeService);

        assertTrue(result);
    }
}