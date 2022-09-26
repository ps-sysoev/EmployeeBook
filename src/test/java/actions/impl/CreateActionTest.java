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

import static org.junit.Assert.*;

public class CreateActionTest {
    private final CreateAction createAction = new CreateAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void whenSave() {
        Input input = Mockito.mock(ConsoleInput.class);

        Mockito.when(input.askStr("Enter name: ")).thenReturn("Vladimir");
        Mockito.when(input.askStr("Enter country: ")).thenReturn("Russia");

        Employee employee = new Employee("Vladimir", "Russia");
        employee.setId(5L);
        employee.setCreated(LocalDateTime.now());

        Mockito.when(employeeMemRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        boolean result = createAction.execute(input, employeeService);

        assertTrue(result);
    }
}