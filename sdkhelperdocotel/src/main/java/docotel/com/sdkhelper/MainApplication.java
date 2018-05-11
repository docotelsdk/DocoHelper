package docotel.com.sdkhelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import docotel.com.sdkhelper.baseutils.ExceptionHandlerHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT = get();

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    public static Context get() {
        return (MainApplication) CONTEXT;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        CONTEXT = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandlerHelper());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.default_font))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
