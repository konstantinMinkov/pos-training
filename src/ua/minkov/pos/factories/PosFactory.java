package ua.minkov.pos.factories;

import ua.minkov.pos.model.Coin;
import ua.minkov.pos.model.Product;
import ua.minkov.pos.pos.Pos;
import ua.minkov.pos.pos.ProductsPos;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class PosFactory {

    public static Pos createProductPos() {
        final NavigableMap<Coin, Integer> coinsHolder = getCoinsAmount();
        Pos pos = new ProductsPos(coinsHolder);
        loadWithProducts(pos);
        return pos;
    }

    private static NavigableMap<Coin, Integer> getCoinsAmount() {
        NavigableMap<Coin, Integer> coins = new TreeMap<>(
                (coin1, coin2) -> -Double.compare(coin1.getValue(), coin2.getValue())
        );
        coins.put(Coin.FIFTY, 10);
        coins.put(Coin.TWENTY_FIVE, 10);
        coins.put(Coin.TEN, 10);
        coins.put(Coin.FIVE, 10);
        coins.put(Coin.ONE, 10);
        return coins;
    }

    private static void loadWithProducts(Pos pos) {
        pos.loadWithProducts(
                Product.COFFEE,
                Product.JUICE,
                Product.TEA
        );
    }
}
