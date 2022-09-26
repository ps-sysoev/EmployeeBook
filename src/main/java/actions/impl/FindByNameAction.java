package actions.impl;

import actions.EmployeeAction;
import input.Input;
import service.EmployeeService;
import util.OutputUtil;

public class FindByNameAction implements EmployeeAction {
    @Override
    public String name() {
        return "Find by name.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        String name = input.askStr("Enter name: ");

        OutputUtil.print(employeeService.findAllByName(name));

        return true;
    }
}
