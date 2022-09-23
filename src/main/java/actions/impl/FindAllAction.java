package actions.impl;

import actions.EmployeeAction;
import input.Input;
import service.EmployeeService;
import sort.Ordered;

public class FindAllAction implements EmployeeAction {
    @Override
    public String name() {
        return "Find all Employees.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        employeeService.findAll(Ordered.DEFAULT).forEach(System.out::println);

        return true;
    }
}
