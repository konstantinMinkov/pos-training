package ua.minkov.pos.pos;

import ua.minkov.pos.model.Coin;
import ua.minkov.pos.model.Payment;
import ua.minkov.pos.model.Product;

import java.util.Map;
import java.util.Set;

/**
 * Created by Konstantin on 27.09.2016.
 */
public interface Pos {

    void insertCoin(Coin coin);
    void loadWithProducts(Product ... products);
    Map<Coin, Integer> getChange();
    Map<Coin, Integer> cancel();
    Payment makePayment();
    void addToCart(Product product);
    Set<Product> getAvailableProducts();
    boolean isProductAvailable(Product product);
    double totalPrice();
    boolean canGiveChange();
    boolean isEnoughInserted();
    double insertedCoinsSum();
}
