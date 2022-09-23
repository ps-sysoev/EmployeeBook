package actions.impl;

import actions.EmployeeAction;
import input.Input;
import service.EmployeeService;

public class DeleteAction implements EmployeeAction {
    @Override
    public String name() {
        return "Delete employee.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        long id = input.askLong("Enter id for delete: ");

        if (employeeService.delete(id)) {
            System.out.println("Deletion has been made.");
        } else {
            System.out.println("Deletion has not been performed.");
        }

        return true;
    }
}
