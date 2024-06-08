package Utils;

import java.text.DecimalFormat;


public class CurrencyConverter {

    public String currencyFormat(double number, String suffix) {
        if (number != 0) {
            DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
            return decimalFormat.format(number) + " " + suffix;
        }
        return "0 " + suffix;
    }
    
    public String currencyFormatInput(double number) {
        if (number != 0) {
            DecimalFormat decimalFormat = new DecimalFormat("###.###");
            return decimalFormat.format(number);
        }
        return "0";
    }
    
    
}
