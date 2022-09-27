package actions.impl;

import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import org.junit.Test;
import org.mockito.Mockito;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;
import sort.Ordered;

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

        // TODO

        boolean result = sortedByOrderAction.execute(input, employeeService);

        assertTrue(result);
    }
}