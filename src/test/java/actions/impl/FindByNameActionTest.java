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

public class FindByNameActionTest {
    private final FindByNameAction findByNameAction = new FindByNameAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenFindByNameTest() {
        Input input = Mockito.mock(ConsoleInput.class);
        Mockito.when(input.askStr("Enter name: ")).thenReturn("Vladimir");

        Employee employee = new Employee("Vladimir", "Russia");
        employee.setId(7L);
        employee.setCreated(LocalDateTime.now());

        Mockito.when(employeeMemRepository.findByName(ArgumentMatchers.eq("Vladimir"))).thenReturn(List.of(employee));

        boolean result = findByNameAction.execute(input, employeeService);

        assertTrue(result);
    }
}