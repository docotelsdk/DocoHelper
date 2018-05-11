package docotel.com.sdkhelper.baseutils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by mzennis on 9/13/16.
 */
public class KeyboardHelper {

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) ApplicationHelper.context().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.findViewById(android.R.id.content).getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    public static void showKeyboard(View edittext) {
        try {
            InputMethodManager imm = (InputMethodManager) ApplicationHelper.context().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edittext, 0);
        } catch (Exception ignored) {
        }
    }
}
