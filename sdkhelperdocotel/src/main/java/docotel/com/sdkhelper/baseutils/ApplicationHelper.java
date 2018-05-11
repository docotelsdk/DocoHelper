package docotel.com.sdkhelper.baseutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

import docotel.com.sdkhelper.MainApplication;

/**
 * Created by docotel on 3/8/16.
 */
public class ApplicationHelper {

    private static final String EMULATOR = "9774d56d682e549c";

    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";

    protected static UUID uuid;


    public static Context context() {
        return MainApplication.CONTEXT;
    }

    public static String getPackageName() {
        return context().getPackageName();
    }

    public static Resources getResources() {
        return context().getResources();
    }

    public static AssetManager getAssetManager() {
        return context().getAssets();
    }

    public static String getLastPackageName() {
        return getPackageName().substring(getPackageName().lastIndexOf(".") + 1);
    }

    @Nullable
    public static String getName() {
        try {
            final PackageManager packageManager = packageManager();
            if (packageManager == null) {
                LoggerHelper.warning("PackageManager was NULL");
                return null;
            }

            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            if (applicationInfo == null) {
                LoggerHelper.warning("ApplicationInfo was NULL");
                return null;
            }

            final CharSequence label = applicationInfo.loadLabel(packageManager);
            if (label == null) {
                LoggerHelper.warning("Label was NULL");
                return null;
            }

            return label.toString();
        } catch (final Exception e) {
            LoggerHelper.wtf(e);
            return null;
        }
    }

    @Nullable
    public static Bitmap getIcon() {
        try {
            final PackageManager packageManager = packageManager();
            if (packageManager == null) {
                LoggerHelper.warning("PackageManager was NULL");
                return null;
            }

            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            if (applicationInfo == null) {
                LoggerHelper.warning("ApplicationInfo was NULL");
                return null;
            }

            final Drawable drawable = applicationInfo.loadIcon(packageManager);
            return BitmapHelper.fromDrawable(drawable);
        } catch (final Exception e) {
            LoggerHelper.wtf(e);
            return null;
        }
    }

    @Nullable
    public static String getVersion() {
        try {
            final PackageManager packageManager = packageManager();
            if (packageManager == null) {
                LoggerHelper.warning("PackageManager was NULL");
                return null;
            }

            final PackageInfo applicationInfo = packageManager.getPackageInfo(getPackageName(), 0);
            if (applicationInfo == null) {
                LoggerHelper.warning("ApplicationInfo was NULL");
                return null;
            }

            return applicationInfo.versionName;

        } catch (Exception e) {
            LoggerHelper.wtf(e);
            return null;
        }
    }

    public static PackageManager packageManager() {
        return context().getPackageManager();
    }

    @Nullable
    public static String androidId() {
//        PreferenceHelper preferenceHelper = new PreferenceHelper(context());
//        String androidId = preferenceHelper.getString(PreferenceHelper.ANDROIDID);
//
//        if (androidId == null) {
        String androidId = getDeviceIMEI();
//        }

//        String androidId = Settings.Secure.getString(context().getContentResolver(), Settings.Secure.ANDROID_ID);
//        final String androidId = "d4ff60ff61abff3f";    // Ledis, Mindo
//        final String androidId = "a837026ac95f8bf3";    // badengopi@gmail.com(123Abc)
//        final String androidId = "9109a2b48c79b134";    //alfari@docotel.com(1234PastiSukses)
//        final String androidId = "e4ff60ff61abff5f";    // tes
//        final String androidId = "54C8017A-6FCC-49D8-82D9-BAFDE7D49FFE";    //customtest@yahoo.com
//        final String androidId = "56aa4e05761aac20";    // candra.ronny@docotel.co.id
//        final String androidId = "d4ff60ff61abff3f";    // steven (1234Qwe)
//        final String androidId = "903388b83a8cef21";    //sonny@docotel.com (Qwe123)
//        final String androidId = "d4ff60ff61abff3a";    //anas@docotel.com (Abc123) dev
//        final String androidId = "913388b83a8cef22";    // kita.mangkal@gmail.com (Qwe123) dev
//        final String androidId = "34c01c6fe1c9038b";    // kita.mangkal@gmail.com (Qwe123) uat
//        final String androidId = "6551c496118c775e";    // mimi
//        final String androidId = "6ec0e1b0ee6954a0";    // customtest@yahoo.com (1234Qwe)
//        final String androidId = "9E3E133C-3EEC-4032-B48F-7D6529D9AE4E";    // sonny@docotel.com (1234Qwe)
//        final String androidId = "356048080613382";    // nokia.docotel@gmail.com (Qwe123) Dev
//        final String androidId = "8b938e8db2d1ae30";    // maisah@yahoo.com (Maisah97) Dev
        if (TextUtils.isEmpty(androidId)) {
            LoggerHelper.warning("AndroidId was NULL");
            return null;
        }

        if (androidId.equals(EMULATOR)) {
            LoggerHelper.warning("EMULATOR");
        }

        return androidId.toLowerCase();
    }

    public static String getDeviceIMEI() {
        TelephonyManager tManager = (TelephonyManager) context().getSystemService(Context.TELEPHONY_SERVICE);
        PreferenceHelper preferenceHelper = new PreferenceHelper(context());
        String deviceUniqueIdentifier = null;

        if (Build.VERSION.SDK_INT < 26) {
            if (null != tManager) {
                deviceUniqueIdentifier = Settings.Secure.getString(context().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        } else {
            try {
                deviceUniqueIdentifier = tManager.getDeviceId();
            } catch (Exception e) {
            }
        }
        preferenceHelper.putString(PreferenceHelper.ANDROIDID, deviceUniqueIdentifier);

        return deviceUniqueIdentifier;
    }
}
