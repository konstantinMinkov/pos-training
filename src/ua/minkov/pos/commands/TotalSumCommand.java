package ua.minkov.pos.commands;

import ua.minkov.pos.pos.Pos;

import java.util.Scanner;

/**
 * Created by Kostiantyn_Minkov on 9/28/2016.
 */
public class TotalSumCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        return "Total: $" + pos.totalPrice();
    }
}
