package ua.minkov.pos.commands;

import ua.minkov.pos.model.Coin;
import ua.minkov.pos.pos.Pos;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by Kostiantyn_Minkov on 9/28/2016.
 */
public class ReturnChangeCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        Map<Coin, Integer> change = pos.getChange();
        StringBuilder builder = new StringBuilder();
        if (change.isEmpty()) {
            return "You have no change.";
        }
        change.forEach((coin, quantity) -> builder.append(" - " + quantity + ", " + coin));
        return builder.toString();
    }
}
