package mapper;

import model.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    public void firstTest() {
        Employee employee = new Employee("Vladimir", "Moscow");

        System.out.println(employeeMapper.fromEmployeeToEmployeeDto(employee));
    }

}