package ua.minkov.pos.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class Payment {

    private LocalDateTime dateTime = LocalDateTime.now();
    private Map<Product, ProductsPack> products;
    private double totalSum;

    public Payment(double totalSum, Map<Product, ProductsPack> products) {
        this.totalSum = totalSum;
        this.products = products;
    }

    public void create() {
        System.out.println("Payment created on " + dateTime.toString());
    }

    public String print() {
        final StringBuilder builder = new StringBuilder();
        builder.append("-----------------------");
        builder.append(" date: " + dateTime.toString());
        products.values().forEach(productPack -> builder.append(productPack.print()));
        return builder.toString();
    }
}
