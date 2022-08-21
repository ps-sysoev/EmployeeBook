package start;

import model.Employee;
import service.EmployeeService;

import java.util.Scanner;

public class StartUI {
    private static void showMenu() {
        System.out.println("Service for working with the employee directory.");
        System.out.println("1. Save.");
        System.out.println("2. Delete.");
        System.out.println("3. Find all.");
        System.out.println("4. Exit.");
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            showMenu();

            System.out.print("select the command: ");
            int value = scanner.nextInt();

            if (value == 1) {
                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.print("Enter country: ");
                String country = scanner.next();

                Employee employee = new Employee(name, country);

                employeeService.save(employee);
            } else if (value == 2) {
                System.out.print("Enter id for delete: ");
                long id = scanner.nextLong();

                employeeService.delete(id);
            } else if (value == 3) {
                for (Employee employee : employeeService.findAll()) {
                    System.out.println(employee);
                }
            } else if (value == 4) {
                isRunning = false;

                System.out.println("Exit program.");
            }
        }
    }
}