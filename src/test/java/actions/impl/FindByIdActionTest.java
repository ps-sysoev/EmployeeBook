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
import java.util.Optional;

import static org.junit.Assert.*;

public class FindByIdActionTest {
    private final FindByIdAction findByIdAction = new FindByIdAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenFindByIdTest() {
        Input input = Mockito.mock(ConsoleInput.class);
        Mockito.when(input.askLong("Enter id: ")).thenReturn(500L);

        Employee employee = new Employee("Vladimir", "Russia");
        employee.setId(500L);
        employee.setCreated(LocalDateTime.now());

        Mockito.when(employeeMemRepository.findById(ArgumentMatchers.eq(500L))).thenReturn(Optional.of(employee));

        boolean result = findByIdAction.execute(input, employeeService);

        assertTrue(result);
    }
}