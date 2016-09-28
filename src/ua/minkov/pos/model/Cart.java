package ua.minkov.pos.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class Cart {

    private Map<Product, ProductsPack> productsPacks;

    public Cart() {
        productsPacks = new HashMap<>();
        productsPacks.put(Product.COFFEE, new ProductsPack(Product.COFFEE));
        productsPacks.put(Product.TEA, new ProductsPack(Product.TEA));
        productsPacks.put(Product.JUICE, new ProductsPack(Product.JUICE));
    }

    public void addProduct(Product product) {
        ProductsPack productsPack = productsPacks.get(product);
        if (productsPack != null) {
            productsPack.addProduct();
        }
    }

    public Payment makePayment() {
        Payment payment = new Payment(totalPrice(), productsPacks);
        payment.create();
        return payment;
    }

    public double totalPrice() {
        return productsPacks.values().stream()
                .mapToDouble(ProductsPack::totalPrice)
                .sum();
    }
}
