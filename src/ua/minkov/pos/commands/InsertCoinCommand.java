package ua.minkov.pos.commands;

import ua.minkov.pos.model.Coin;
import ua.minkov.pos.pos.Pos;

import java.util.Arrays;
import java.util.Scanner;

public class InsertCoinCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        System.out.print("Please, insert coins: \n");
        Arrays.stream(Coin.values()).forEach(coin -> System.out.print("$" + coin.getValue() + "; "));
        System.out.println();
        while ( !scanner.hasNext());
        try {
            double value = scanner.nextDouble();
            Coin coin = Coin.getByValue(value);
            if (coin == null) throw new IllegalArgumentException();
            pos.insertCoin(coin);
        } catch (Exception e) {
            System.out.println("Not a proper coin nominal.");
        }
        return "Total: $" + pos.totalPrice() + "\nYour balance: $" + pos.insertedCoinsSum();
    }
}
