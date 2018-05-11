package docotel.com.sdkhelper;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

// credits : http://stackoverflow.com/questions/11790102/format-credit-card-in-edit-text-in-android

public class CreditCardTextWatcher implements TextWatcher {

    // Change this to what you want... ' ', '-' etc..
    private char separator = ' ';

    public CreditCardTextWatcher(char separator) {
        this.separator = separator;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // do nothing
    }

    @Override
    public synchronized void afterTextChanged(Editable s) {
        if (s.length() >0) {
            final char c = s.charAt(s.length() - 1);
            if (separator == c) {
                s.delete(s.length() - 1, s.length());
            }
        }
        // Insert char where needed.
        if (s.length() > 0 && (s.length() % 5) == 0) {
            final char c = s.charAt(s.length() - 1);
            // Only if its a digit where there should be a separator we insert a separator
            if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(separator)).length <= 3) {
                s.insert(s.length() - 1, String.valueOf(separator));
            }
        }
    }
}
