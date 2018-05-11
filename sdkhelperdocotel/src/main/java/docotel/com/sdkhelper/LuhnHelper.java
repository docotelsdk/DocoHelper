package docotel.com.sdkhelper;

/**
 * Created by agusn on 05/01/2018 19.02.
 */


public class LuhnHelper {
    public static String getValidCC(String ccNumber)
    {
        String validCc="";
        for(int i=0; i<=9; i++){
            if(isValidCc(ccNumber+i)){
                validCc=ccNumber+i;
                break;
            }
        }
        return validCc;
    }
    private static boolean isValidCc(String ccNumber){
        int sum = 0;
        boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
            return (sum % 10 == 0);
    }
}
