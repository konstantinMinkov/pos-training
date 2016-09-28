package ua.minkov.pos;

import ua.minkov.pos.model.Coin;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class CoinsManager {

    private NavigableMap<Coin, Integer> availableCoins;
    private Map<Coin, Integer> insertedCoins;

    public CoinsManager(NavigableMap<Coin, Integer> availableCoins) {
        this.availableCoins = availableCoins;
        insertedCoins = new HashMap<>();
    }

    public Map<Coin, Integer> returnInsertedCoins() {
        final Map<Coin, Integer> tempStorage = insertedCoins;
        insertedCoins = new HashMap<>();
        return tempStorage;
    }

    public void insertCoin(Coin coin) {
        int quantity = 1;
        if (insertedCoins.containsKey(coin)) {
            quantity += insertedCoins.get(coin);
        }
        insertedCoins.put(coin, quantity);
    }

    public double insertedCoinsSum() {
        return insertedCoins.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getValue() * entry.getValue())
                .sum();
    }

    public Map<Coin, Integer> getChange(double totalPrice) {
        double change;
        if ( !isEnoughInserted(totalPrice)) return null;
        change = insertedCoinsSum() - totalPrice;
        if ( !canGiveChange(change)) return null;
        return pollChange(change);
    }

    private Map<Coin, Integer> countChange(double change) {
        Map<Coin, Integer> changeCoins = new HashMap<>();
        Map.Entry<Coin, Integer> entry;
        while (change > 0.000001) {
            final double changeLeft = change;
            entry = availableCoins.entrySet().stream()
                    .filter(e -> e.getValue() > 0
                            && e.getKey().getValue() <= changeLeft)
                    .findFirst()
                    .orElse(null);
            if (entry == null) {
                moveAllCoins(changeCoins, availableCoins);
                return null;
            }
            change -= entry.getKey().getValue();
            moveCoin(availableCoins, changeCoins, entry.getKey());
        }
        return changeCoins;
    }

    private Map<Coin, Integer> pollChange(double change) {
        final Map<Coin, Integer> changeCoins = countChange(change);
        if (changeCoins != null) {
            changeCoins.forEach((coin, quantity) ->
                    availableCoins.merge(coin, quantity, (oldValue, value) -> oldValue - value));
        }
        return changeCoins;
    }

    private void moveAllCoins(Map<Coin, Integer> from, Map<Coin, Integer> to) {
        from.forEach((coin, quantity) -> to.merge(coin, quantity, Integer::sum));
    }

    private void moveCoin(Map<Coin, Integer> from, Map<Coin, Integer> to, Coin coin) {
        Integer toQuantity = to.get(coin);
        Integer fromQuantity = from.get(coin);
        if (fromQuantity < 1) return;
        from.put(coin, fromQuantity - 1);
        if (toQuantity != null) {
            to.put(coin, toQuantity);
        }
        to.put(coin, 1);
    }

    public boolean canGiveChange(double totalPrice) {
        return isEnoughInserted(totalPrice)
                && countChange(insertedCoinsSum() - totalPrice) != null;
    }

    public boolean isEnoughInserted(double totalPrice) {
        return insertedCoinsSum() >= totalPrice;
    }
}
