package docotel.com.sdkhelper;

public class SecretKeyHelper {

    private static String DYNAMIC_KEY = "SecretKey";
    private static String key;

    public static String defaultKey() {
//        return getDefaultKeyConsumerProd();
//        return getDefaultKeyConsumerUat();
          return getDefaultKeyConsumerDev();
    }

    public static String dynamicKey() {
        if (key == null) return "";
        else return key;
    }

    public static void setDynamicKey(String currentKey) {
//      PreferenceUtils.putString(DYNAMIC_KEY, currentKey);
        key = currentKey;
    }

    static {
        System.loadLibrary("docotel");
    }

    public native static String getDefaultKeyConsumerUat();
    public native static String getDefaultKeyConsumerProd();
    public native static String getDefaultKeyConsumerDev();
}
