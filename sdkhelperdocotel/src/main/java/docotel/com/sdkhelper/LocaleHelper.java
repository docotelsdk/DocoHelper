package docotel.com.sdkhelper;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import static docotel.com.sdkhelper.baseutils.ApplicationHelper.getResources;

/**
 * Project Name  : mVISA-consumer
 * Created By    : naufal
 * Date Creation : 2017-Feb-28
 */

public class LocaleHelper {

    public static Locale getLocale() {
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        return conf.locale;
    }

    public static void setLocale(String locale) {
        Locale appLocale = new Locale(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = appLocale;
        res.updateConfiguration(conf, dm);
    }

}
