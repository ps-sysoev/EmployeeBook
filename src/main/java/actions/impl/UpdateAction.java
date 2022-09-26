package actions.impl;

import actions.EmployeeAction;
import dto.EmployeeDto;
import input.Input;
import service.EmployeeService;

public class UpdateAction implements EmployeeAction {
    @Override
    public String name() {
        return "Update employee.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        long id = input.askLong("Enter id: ");
        String name = input.askStr("Enter name: ");
        String country = input.askStr("Enter country: ");

        boolean updateResult = employeeService.update(id, new EmployeeDto(name, country));

        if (updateResult) {
            System.out.println("Successfully.");
        } else {
            System.out.println("Unsuccessfully.");
        }

        return true;
    }
}
