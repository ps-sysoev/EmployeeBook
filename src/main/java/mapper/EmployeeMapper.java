package mapper;

import dto.EmployeeDto;
import model.Employee;
import util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Маппер для класса Employee
 */
public class EmployeeMapper {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.PATTERN_DATE);

    /**
     * Преобразует сущность в DTO +парсит дату в формат: 1980-10-29 16:55
     *
     * @param employee entity
     * @return dto
     */
    public EmployeeDto fromEmployeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .name(employee.getName())
                .country(employee.getCountry())
                .created(employee.getCreated().format(dateTimeFormatter))
                .build();
    }

    /**
     * Преобразует dto в сущность
     * @param employeeDto  Dto
     * @return сущность
     */
    public Employee fromEmployeeDtoToEmployeeEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .name(employeeDto.getName())
                .country(employeeDto.getCountry())
                .created(LocalDateTime.now())
                .build();
    }

    /**
     * Получаем из строки корректную дату
     * @param date строка с датой
     * @return результат парсинга - корректная дата
     */
    public LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
