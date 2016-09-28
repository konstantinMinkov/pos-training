package ua.minkov.pos;

import ua.minkov.pos.commands.Command;
import ua.minkov.pos.factories.PosFactory;
import ua.minkov.pos.pos.Pos;

import java.util.Scanner;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class Main {

    private static Pos pos = PosFactory.createProductPos();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String commandName;
        Command command;
        while (true) {
            if (scanner.hasNext()) {
                commandName = scanner.next();
                command = CommandProvider.getCommand(commandName);
                System.out.println(command.perform(scanner, pos));
            }
        }
    }
}
