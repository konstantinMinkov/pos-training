package ua.minkov.pos.commands;

import ua.minkov.pos.model.Coin;
import ua.minkov.pos.pos.Pos;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by Kostiantyn_Minkov on 9/28/2016.
 */
public class CancelPurchaseCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        Map<Coin, Integer> moneyBack = pos.cancel();
        final StringBuilder builder = new StringBuilder();
        builder.append("Your order was canceled.\n Your change: \n");
        moneyBack.entrySet().forEach(entry -> {
            builder.append(" - $" + entry.getKey().getValue());
            builder.append(" - " + entry.getValue() + " coins.\n");
        });
        return builder.toString();
    }
}
