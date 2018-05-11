package docotel.com.sdkhelper;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import org.apache.commons.lang3.StringUtils;

public class TSnackbarHelper {

    public static TSnackbar error(View view, String message, int length) {
        return error(view, message, length, false);
    }

    public static TSnackbar error(View view, String message, int length, boolean fullscreenWindow) {
        if (!message.substring(message.length() - 1).equals("."))
            message = StringUtils.capitalize(message.toLowerCase()) + ".";
        TSnackbar snackBar = TSnackbar.make(view, message, length);
        View snackBarView = snackBar.getView();
        snackBarView.setBackgroundColor(Color.RED);
        TextView textView = (TextView) snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (fullscreenWindow) params.setMargins(8, 48, 8, 16);
        else params.setMargins(8, 16, 8, 16);
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(params);
        snackBar.show();
        return snackBar;
    }

    public static TSnackbar info(View view, String message, int length) {
        return info(view, message, length, false);
    }

    public static TSnackbar info(View view, String message, int length, boolean fullscreenWindow) {
        if (!message.substring(message.length() - 1).equals("."))
            message = StringUtils.capitalize(message.toLowerCase()) + ".";
        TSnackbar snackBar = TSnackbar.make(view, message, length);
        View snackBarView = snackBar.getView();
        snackBarView.setBackgroundColor(Color.BLUE);
        TextView textView = (TextView) snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (fullscreenWindow) params.setMargins(8, 48, 8, 16);
        else params.setMargins(8, 16, 8, 16);
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(params);
        snackBar.show();
        return snackBar;
    }
}
