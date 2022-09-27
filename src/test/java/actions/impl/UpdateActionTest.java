package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import model.Employee;
import org.junit.Test;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;

import static org.junit.Assert.*;

public class UpdateActionTest {
    private final UpdateAction updateAction = new UpdateAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenUpdateTest() {
        Input input = Mockito.mock(ConsoleInput.class);

        Mockito.when(input.askLong("Enter id: ")).thenReturn(5L);
        Mockito.when(input.askStr("Enter name: ")).thenReturn("Vladimir");
        Mockito.when(input.askStr("Enter country: ")).thenReturn("Russia");

        Mockito.when(employeeMemRepository.update(
                Mockito.anyLong(),
                Mockito.any(Employee.class)
        )).thenReturn(true);

        boolean result = updateAction.execute(input, employeeService);

        assertTrue(result);
    }
}