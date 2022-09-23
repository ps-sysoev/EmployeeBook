package actions.impl;

import actions.EmployeeAction;
import dto.EmployeeDto;
import input.Input;
import service.EmployeeService;

import java.util.List;

public class FindByNameAction implements EmployeeAction {
    @Override
    public String name() {
        return "Find by name.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        String name = input.askStr("Enter name: ");

        List<EmployeeDto> employeeList = employeeService.findAllByName(name);

        if (employeeList.isEmpty()) {
            System.out.println("Employees not found.");
        } else {
            employeeList.forEach(System.out::println);
        }

        return true;
    }
}
