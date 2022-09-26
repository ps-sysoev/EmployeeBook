package mapper;

import dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import model.Employee;
import org.junit.Test;

import java.time.DateTimeException;

import static org.junit.Assert.*;

@Slf4j
public class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    public void fromEmployeeToEmployeeDtoTest() {
        try {
            Employee employee = new Employee("Vladimir", "Moscow");

            log.info("Employee DTO: {}", employeeMapper.fromEmployeeToEmployeeDto(employee));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void fromEmployeeDtoToEmployeeTest() {
        try {
            EmployeeDto employee = new EmployeeDto("Vladimir", "Moscow");

            log.info("Employee: {}", employeeMapper.fromEmployeeDtoToEmployeeEntity(employee));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDateTest() {
        try {
            employeeMapper.parseDate("26-09-2022 21:18");
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = DateTimeException.class)
    public void whenParseDateNotSuccessTest() {
        employeeMapper.parseDate("26.09.2022 21:18");
    }
}