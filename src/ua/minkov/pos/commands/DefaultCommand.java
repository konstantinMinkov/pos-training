package ua.minkov.pos.commands;

import ua.minkov.pos.pos.Pos;

import java.util.Scanner;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class DefaultCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        return "No such command, please, ask me something else.";
    }
}
