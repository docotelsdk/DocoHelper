package docotel.com.sdkhelper.baseutils;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by mzennis on 11/9/16.
 */
public class CurrencyHelper {
    public static String displayCurrency(String number, boolean withCommaStrip) {
        String displayedString = "";
        if (number.length() > 3) {
            int length = number.length();

            for (int i = length; i > 0; i -= 3) {
                if (i > 3) {
                    String myStringPrt1 = number.substring(0, i - 3);
                    String myStringPrt2 = number.substring(i - 3);
                    String combinedString = myStringPrt1 + ".";
                    combinedString = combinedString + myStringPrt2;
                    number = combinedString;
                    displayedString = combinedString;
                }
            }
        } else {
            displayedString = number;
        }
        if (withCommaStrip) {
            return displayedString + ",-";
        }
        return displayedString;
    }

    public static DecimalFormat formatter() {
        return new DecimalFormat("###");
    }

    public static String rupiahCurrencyWithSign(String sign, BigInteger nominal, boolean withCommaStrip) {
        String s_display = "";
//        if (nominal.signum() == -1) {
//            s_display = "- ";
//            nominal = nominal.multiply(new BigInteger("-1"));
//        } else {
//            s_display = "+ ";
//        }
        if (sign != null) {
            s_display = s_display + sign + " ";
        }
        return s_display + displayCurrency(String.valueOf(nominal), withCommaStrip);
    }

    public static String getDecoratedBalance(String balance) {
        if (balance.contains(".00")) {
            balance = balance.substring(0, balance.length() - 3);
            LoggerHelper.info(balance);
        }
        return "Rp " + displayCurrency(balance, false) + ",-";
    }

    public static String displayCurrency(String number, char separator) {
        if (number.equals("")) return "0";
        String displayedString = "";
        if(number.length() > 3) {
            int length = number.length();
            for(int i = length; i > 0; i -= 3) {
                if(i > 3) {
                    String myStringPrt1 = number.substring(0, i - 3);
                    String myStringPrt2 = number.substring(i - 3);
                    String combinedString = myStringPrt1 + separator;
                    combinedString = combinedString + myStringPrt2;
                    number = combinedString;
                    displayedString = combinedString;
                }
            }
        } else {
            displayedString = number;
        }
        return displayedString;
    }

    public static String displayCurrency(String number, char separator, String prefix, String suffix) {
        return prefix + displayCurrency(number, separator) + suffix;
    }

}
