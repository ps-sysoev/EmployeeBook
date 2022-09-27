package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import model.Employee;
import org.junit.Test;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FindAllActionTest {
    private final FindAllAction findAllAction = new FindAllAction();
    private final EmployeeMemRepository employeeMemRepository = Mockito.mock(EmployeeMemRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeMemRepository, employeeMapper);

    @Test
    public void findAllTest() {
        Input input = Mockito.mock(ConsoleInput.class);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Vladimir", "Russia"));
        employeeList.add(new Employee("Boris", "Russia"));
        employeeList.add(new Employee("Alexander", "Belarus"));

        Mockito.when(employeeMemRepository.findAll()).thenReturn(employeeList);

        boolean result = findAllAction.execute(input, employeeService);

        assertTrue(result);
    }
}