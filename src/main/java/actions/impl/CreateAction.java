package actions.impl;

import actions.EmployeeAction;
import input.Input;
import model.Employee;
import service.EmployeeService;

public class CreateAction implements EmployeeAction {

    @Override
    public String name() {
        return "Create a new employee.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        String name = input.askStr("Enter name: ");
        String country = input.askStr("Enter country: ");

        Employee employee = new Employee(name, country);

        return employeeService.save(employee);
    }
}
