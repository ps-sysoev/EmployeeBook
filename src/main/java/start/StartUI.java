package start;

import actions.*;
import actions.impl.*;
import input.ConsoleInput;
import input.Input;
import mapper.EmployeeMapper;
import model.Employee;
import repository.Repository;
import repository.impl.EmployeeMemRepository;
import service.EmployeeService;

public class StartUI {
    private void showMenu(EmployeeAction[] employeeAction) {
        System.out.println("************************************");
        System.out.println("Welcome it is menu.");

        for (int i = 0; i < employeeAction.length; i++) {
            System.out.println("[" + i + "] " + employeeAction[i].name());
        }

        System.out.println("************************************");
    }

    public void init(Input input, EmployeeService employeeService, EmployeeAction[] employeeAction) {
        boolean isRunning = true;

        while (isRunning) {
            showMenu(employeeAction);

            int select = (int) input.askLong("Select the command: ");
            int employeeActionLength = employeeAction.length;

            if (select < 0 || select >= employeeActionLength) {
                System.out.println("Input error. You have to choose: 0 .. " + (employeeActionLength - 1));

                continue;
            }

            EmployeeAction action = employeeAction[select];
            isRunning = action.execute(input, employeeService);
        }
    }

    public static void main(String[] args) {
        final Input input = new ConsoleInput();

        final Repository<Long, Employee> store = new EmployeeMemRepository();

        final EmployeeService employeeService = new EmployeeService(
                store,
                new EmployeeMapper()
        );

        final EmployeeAction[] actions = {
                new CreateAction(),
                new UpdateAction(),
                new DeleteAction(),
                new FindAllAction(),
                new FindByIdAction(),
                new FindByIntervalDateAction(),
                new FindByNameAction(),
                new SortedByOrderAction(),
                new ExitProgramAction()
        };

        new StartUI().init(input, employeeService, actions);
    }
}