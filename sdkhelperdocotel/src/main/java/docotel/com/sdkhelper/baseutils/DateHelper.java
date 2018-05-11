package docotel.com.sdkhelper.baseutils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static java.util.Locale.getDefault;

/**
 * Project Name  : docotel-android-libraries
 * Package Name  : com.docotel.libraries.helper
 * Created By    : mzennis, naufal
 * Date Creation : 2016-Nov-11
 */
public class DateHelper {

    public static final String mobile_date_format = "dd/MM/yyyy";
    public static final String service_date_format = "yyyy-MM-dd";
    public static final String credit_card_valid_format = "yyMM";
    public static final String credit_card_invalid_format = "MM/yy";
    public static final String dob_valid_format = "ddMMyyyy";

    public static int day() {
        return getInstance().get(DAY_OF_MONTH);
    }

    public static int day(Calendar calendar) {
        return calendar.get(DAY_OF_MONTH);
    }

    public static int month() {
        return getInstance().get(MONTH);
    }

    public static int month(Calendar calendar) {
        return calendar.get(MONTH);
    }

    public static int year() {
        return getInstance().get(YEAR);
    }

    public static int year(Calendar calendar) {
        return calendar.get(YEAR);
    }

    static long timestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static Date date() {
        final Calendar cal = getInstance();
        cal.setTimeInMillis(timestamp());
        return cal.getTime();
    }

    public static SimpleDateFormat dateFormat(String format) {
        return new SimpleDateFormat(format, getDefault());
    }

    public static String changeFormat(String stringDate, @NonNull String prevFormat, @NonNull String toFormat) {
        DateFormat df_prevFormat = dateFormat(prevFormat);
        DateFormat df_toFormat = dateFormat(toFormat);
        Date date;
        if (stringDate != null) {
            try {
                date = df_prevFormat.parse(stringDate);
            } catch (ParseException e) {
                return "";
            }
            return df_toFormat.format(date);
        }
        return "";
    }

    public static void datePicker(@NonNull Context context, @NonNull final DatePickerDialogListener listener, @NonNull Date defaultDate, final int requestCode, final String requestedFormat) {
        Calendar cal_defaultDate = getInstance();
        cal_defaultDate.setTime(defaultDate);
        new DatePickerDialog(context, (datePicker, i, i1, i2) -> listener.onDateSet(requestCode, dateFormat(requestedFormat).format(new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()).getTime())), year(cal_defaultDate), month(cal_defaultDate), day(cal_defaultDate)).show();
    }

    public static Date today() {
        return Calendar.getInstance().getTime();
    }

    public interface DatePickerDialogListener {
        void onDateSet(int requestCode, @NonNull String date);
    }

    public static String parseDateTimeTransaction(String dateTimeTransaction) { // dateTimeTransaction must be formatted on "yyyy-MM-dd", ignore the time ~
        String date = dateTimeTransaction.substring(0, 10);
        String time = dateTimeTransaction.substring(11, dateTimeTransaction.length());
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        GregorianCalendar calendar = new GregorianCalendar(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
        if (isToday(calendar)) date = "Today";
        if (isYesterday(calendar)) date = "Yesterday";
        return date + " " + time;
    }

    private static boolean isToday(@NonNull GregorianCalendar calendar) {
        GregorianCalendar today = new GregorianCalendar();
        return today.get(YEAR) == calendar.get(YEAR) && today.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
    }

    private static boolean isYesterday(@NonNull GregorianCalendar calendar) {
        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);
        return yesterday.get(YEAR) == calendar.get(YEAR) && yesterday.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
    }
}