package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;

import static org.junit.Assert.*;

public class DeleteActionTest {
    private final DeleteAction deleteAction = new DeleteAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenDeleteEmployee() {
        Input input = Mockito.mock(ConsoleInput.class);
        Mockito.when(input.askLong("Enter id for delete: ")).thenReturn(5L);

        Mockito.when(employeeMemRepository.delete(ArgumentMatchers.eq(5L))).thenReturn(true);

        boolean result = deleteAction.execute(input, employeeService);

        assertTrue(result);
    }
}