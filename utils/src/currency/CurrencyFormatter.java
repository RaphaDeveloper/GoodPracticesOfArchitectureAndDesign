package currency;

import java.text.NumberFormat;

public class CurrencyFormatter {

    public static String format(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
