package utils;

import java.text.NumberFormat;

public class CurrencyUtils {

    public static String formatValueToCurrency(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
