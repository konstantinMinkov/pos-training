package ua.minkov.pos.model;

import java.util.Arrays;

/**
 * Created by Konstantin on 27.09.2016.
 */
public enum Coin {

    ONE(.01), FIVE(.05), TEN(.1), TWENTY_FIVE(.25), FIFTY(.5);

    private double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Coin getByValue(double value) {
        return Arrays.stream(values())
                .filter(coin -> coin.getValue() == value)
                .findAny()
                .get();
    }

    @Override
    public String toString() {
        return (int)(value * 100) + "c";
    }
}
