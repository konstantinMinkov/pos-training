package ua.minkov.pos.factories;

import ua.minkov.pos.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class ProductFactory {

    private static Map<String, Product> products;

    static {
        products = new HashMap<>();
        products.put("tea", Product.TEA);
        products.put("coffee", Product.COFFEE);
        products.put("juice", Product.JUICE);
    }

    public static Product getProduct(String name) {
        return products.get(name);
    }
}
