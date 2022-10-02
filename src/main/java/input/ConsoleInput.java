package input;

import util.Constants;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);

        return scanner.nextLine();
    }

    @Override
    public long askLong(String question) {
        String line = askStr(question);

        return line.matches("\\d+") ? Long.parseLong(line) : Constants.INCORRECT_ID;
    }
}
