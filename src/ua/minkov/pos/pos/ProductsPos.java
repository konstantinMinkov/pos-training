package ua.minkov.pos.pos;

import ua.minkov.pos.CoinsManager;
import ua.minkov.pos.model.Coin;
import ua.minkov.pos.model.Cart;
import ua.minkov.pos.model.Payment;
import ua.minkov.pos.model.Product;

import java.util.*;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class ProductsPos implements Pos {

    private Cart cart;
    private CoinsManager coinsManager;
    private Set<Product> availableProducts;

    public ProductsPos(NavigableMap<Coin, Integer> availableCoins) {
        coinsManager = new CoinsManager(availableCoins);
        cart = new Cart();
        availableProducts = new HashSet<>();
    }

    @Override
    public void loadWithProducts(Product... products) {
        Arrays.stream(products).forEach(availableProducts::add);
    }

    @Override
    public Map<Coin, Integer> cancel() {
        emptyCart();
        return coinsManager.returnInsertedCoins();
    }

    @Override
    public double insertedCoinsSum() {
        return coinsManager.insertedCoinsSum();
    }

    @Override
    public Map<Coin, Integer> getChange() {
        return coinsManager.getChange(totalPrice());
    }

    @Override
    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    @Override
    public Payment makePayment() {
        Payment payment = null;
        if (isEnoughInserted() && canGiveChange()) {
            payment = cart.makePayment();
            emptyCart();
        }
        return payment;
    }

    @Override
    public double totalPrice() {
        return cart.totalPrice();
    }

    @Override
    public void insertCoin(Coin coin) {
        coinsManager.insertCoin(coin);
    }

    @Override
    public boolean canGiveChange() {
        return coinsManager.canGiveChange(totalPrice());
    }

    @Override
    public boolean isEnoughInserted() {
        return coinsManager.isEnoughInserted(totalPrice());
    }

    @Override
    public boolean isProductAvailable(Product product) {
        return availableProducts.contains(product);
    }

    @Override
    public Set<Product> getAvailableProducts() {
        return Collections.unmodifiableSet(availableProducts);
    }

    private void emptyCart() {
        cart = new Cart();
    }
}
