package actions.impl;

import actions.EmployeeAction;
import input.Input;
import service.EmployeeService;
import util.Constants;

import java.time.format.DateTimeParseException;

public class FindByIntervalDateAction implements EmployeeAction {
    @Override
    public String name() {
        return "Find employees by interval date.";
    }

    @Override
    public boolean execute(Input input, EmployeeService employeeService) {
        System.out.printf("Please enter date by format: %s\n", Constants.PATTERN_DATE);

        String begin = input.askStr("Enter begin date: ");
        String end = input.askStr("Enter end date: ");

        try {
            employeeService.findAllByIntervalDate(begin, end)
                    .forEach(System.out::println);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format! Please enter the date again.");
        }

        return true;
    }
}
