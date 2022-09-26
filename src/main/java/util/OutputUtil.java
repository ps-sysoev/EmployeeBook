package util;

import dto.EmployeeDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OutputUtil {
    public void print(List<EmployeeDto> employeeDtoList) {
        if (employeeDtoList.isEmpty()) {
            System.out.println("Employees not found.");
        } else {
            employeeDtoList.forEach(System.out::println);
        }
    }
}
