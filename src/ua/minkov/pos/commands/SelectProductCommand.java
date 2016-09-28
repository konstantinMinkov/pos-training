package ua.minkov.pos.commands;

import ua.minkov.pos.factories.ProductFactory;
import ua.minkov.pos.model.Product;
import ua.minkov.pos.pos.Pos;

import java.util.Scanner;

/**
 * Created by Kostiantyn_Minkov on 9/28/2016.
 */
public class SelectProductCommand implements Command {

    @Override
    public String perform(Scanner scanner, Pos pos) {
        System.out.println("Enter one of products name.");
        for (Product product : pos.getAvailableProducts()) {
            System.out.println(" - " + product.getName() + " : $" + product.getPrice());
        }
        while ( !scanner.hasNext());
        final Product product = ProductFactory.getProduct(scanner.next());
        if (pos.isProductAvailable(product)) {
            pos.addToCart(product);
            return "Total: " + pos.totalPrice() + ". \n Do you want to select more, or checkout now?";
        }
        return "No such product, please, try this command again with other one.";
    }
}
