package actions.impl;

import actions.EmployeeAction;
import input.Input;
import service.EmployeeService;
import sort.Ordered;

public class SortedByOrderAction implements EmployeeAction {
    @Override
    public String name() {
        return "Sorted employee by ASC/DESC.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        String orderedType = input.askStr("Enter order type (ASC or DESC): ");

        try {
            employeeService.findAll(Ordered.valueOf(orderedType)).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect order type! Please enter the order type again.");
        }

        return true;
    }
}
