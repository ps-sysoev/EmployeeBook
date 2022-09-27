package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import model.Employee;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FindByIntervalDateActionTest {
    private final FindByIntervalDateAction findByIntervalDateAction = new FindByIntervalDateAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenFindByIntervalDateTest() {
        Input input = Mockito.mock(ConsoleInput.class);

        Mockito.when(input.askStr("Enter begin date: ")).thenReturn("27-09-2022 15:27");
        Mockito.when(input.askStr("Enter end date: ")).thenReturn("27-09-2022 15:45");

        Employee firstEmployee = new Employee("Vladimir", "Russia");
        firstEmployee.setId(5L);
        firstEmployee.setCreated(employeeMapper.parseDate("27-09-2022 15:31"));

        Employee secondEmployee = new Employee("Alexander", "Belarus");
        secondEmployee.setId(7L);
        secondEmployee.setCreated(employeeMapper.parseDate("27-09-2022 15:43"));

        List<Employee> employees = List.of(firstEmployee, secondEmployee);

        Mockito.when(employeeMemRepository.findByCreatedDateInterval(
                Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class)
        )).thenReturn(employees);

        boolean result = findByIntervalDateAction.execute(input, employeeService);

        assertTrue(result);
    }
}