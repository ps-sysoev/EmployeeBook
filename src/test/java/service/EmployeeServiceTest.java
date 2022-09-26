package service;

import dto.EmployeeDto;
import mapper.EmployeeMapper;
import org.junit.Test;
import repository.impl.EmployeeMemRepository;
import sort.Ordered;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(
            new EmployeeMemRepository(),
            employeeMapper
    );

    @Test
    public void whenSortingDefault() {
        employeeService.save(new EmployeeDto("Vladimir", "Russia"));
        employeeService.save(new EmployeeDto("Dmitry", "Russia"));
        employeeService.save(new EmployeeDto("Merkel", "Germany"));

        List<EmployeeDto> employees = employeeService.findAll(Ordered.DEFAULT);

        assertEquals("Vladimir", employees.get(0).getName());
        assertEquals("Dmitry", employees.get(1).getName());
        assertEquals("Merkel", employees.get(2).getName());

        assertEquals(3, employees.size());
    }

    @Test
    public void whenSortingDesc() {
        employeeService.save(new EmployeeDto("Vladimir", "Russia"));
        employeeService.save(new EmployeeDto("Dmitry", "Russia"));
        employeeService.save(new EmployeeDto("Merkel", "Germany"));

        List<EmployeeDto> employees = employeeService.findAll(Ordered.DESC);

        assertEquals("Vladimir", employees.get(0).getName());
        assertEquals("Merkel", employees.get(1).getName());
        assertEquals("Dmitry", employees.get(2).getName());

        assertEquals(3, employees.size());
    }

    @Test
    public void whenSortingAsc() {
        employeeService.save(new EmployeeDto("Vladimir", "Russia"));
        employeeService.save(new EmployeeDto("Dmitry", "Russia"));
        employeeService.save(new EmployeeDto("Merkel", "Germany"));

        List<EmployeeDto> employees = employeeService.findAll(Ordered.ASC);

        assertEquals("Dmitry", employees.get(0).getName());
        assertEquals("Merkel", employees.get(1).getName());
        assertEquals("Vladimir", employees.get(2).getName());

        assertEquals(3, employees.size());
    }

}