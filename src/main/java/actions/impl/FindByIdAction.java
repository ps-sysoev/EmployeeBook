package actions.impl;

import actions.EmployeeAction;
import dto.EmployeeDto;
import input.Input;
import service.EmployeeService;

import java.util.Optional;

public class FindByIdAction implements EmployeeAction {

    @Override
    public String name() {
        return "Find by ID.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        Long id = input.askLong("Enter id: ");

        Optional<EmployeeDto> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            System.out.println(employee.get());
        } else {
            System.out.println("Employees not found.");
        }

        return true;
    }
}
