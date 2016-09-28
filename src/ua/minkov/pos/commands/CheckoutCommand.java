package ua.minkov.pos.commands;

import ua.minkov.pos.model.Payment;
import ua.minkov.pos.pos.Pos;

import java.util.Scanner;

/**
 * Created by Kostiantyn_Minkov on 9/28/2016.
 */
public class CheckoutCommand implements Command {

    //dfsdfsf //todo dont give a change
    @Override
    public String perform(Scanner scanner, Pos pos) {
        if ( !pos.isEnoughInserted()) return "Not enough money inserted!";
        if ( !pos.canGiveChange()) return "Can't give you a change, you can cancel your order, or order something else";
        Payment payment = pos.makePayment();
        return payment.print();
    }
}
