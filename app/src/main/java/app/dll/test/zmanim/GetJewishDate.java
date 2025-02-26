package app.dll.test.zmanim;

import static android.provider.Settings.System.getString;

import android.util.Log;
import android.view.View;

import com.google.protobuf.StringValue;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.HebrewDateFormatter;
import com.kosherjava.zmanim.hebrewcalendar.JewishCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import app.dll.test.R;

public class GetJewishDate {
    public static String getJewishDayAndMonth(int  year, int month, int day, View view) {
        // Create HebrewDateFormatter to get formatted Jewish dates
        HebrewDateFormatter hebrewDateFormatter = new HebrewDateFormatter();
        hebrewDateFormatter.setHebrewFormat(true); // Set to true for Hebrew characters

        // Get Jewish date and calendar
        JewishDate jewishDate = new JewishDate(new GregorianCalendar(year, month, day));
        JewishCalendar jewishCalendar = new JewishCalendar();

        // Get the day of the month
        int dayOfMonth = jewishDate.getJewishDayOfMonth();

        // Get the month of the year
        int monthOfYear = jewishDate.getJewishMonth();

        int jewishYear = jewishDate.getJewishYear();

        //return statement
        return String.valueOf(dayOfMonth) + " " + getJewishMonthName(monthOfYear, jewishYear, view);
    }
    private static String getJewishMonthName(int month, int year, View view) {
        JewishCalendar jewishCalendar = new JewishCalendar();
        jewishCalendar.setJewishYear(year - 1);
        if (jewishCalendar.isJewishLeapYear()) {
            switch (month) {
                case 1:
                    return view.getResources().getString(R.string.nisan);
                case 2:
                    return view.getResources().getString(R.string.iyar);
                case 3:
                    return view.getResources().getString(R.string.sivan);
                case 4:
                    return view.getResources().getString(R.string.tamuz);
                case 5:
                    return view.getResources().getString(R.string.av);
                case 6:
                    return view.getResources().getString(R.string.elul);
                case 7:
                    return view.getResources().getString(R.string.tishrei);
                case 8:
                    return view.getResources().getString(R.string.heshvan);
                case 9:
                    return view.getResources().getString(R.string.kislev);
                case 10:
                    return view.getResources().getString(R.string.tevet);
                case 11:
                    return view.getResources().getString(R.string.shevat);
                case 12:
                    return view.getResources().getString(R.string.adar);
                case 13:
                    return view.getResources().getString(R.string.adar2); // Only in leap years
                default:
                    return "Invalid month";
            }
        } else {
            switch (month) {
                case 1:
                    return view.getResources().getString(R.string.nisan);
                case 2:
                    return view.getResources().getString(R.string.iyar);
                case 3:
                    return view.getResources().getString(R.string.sivan);
                case 4:
                    return view.getResources().getString(R.string.tamuz);
                case 5:
                    return view.getResources().getString(R.string.av);
                case 6:
                    return view.getResources().getString(R.string.elul);
                case 7:
                    return view.getResources().getString(R.string.tishrei);
                case 8:
                    return view.getResources().getString(R.string.heshvan);
                case 9:
                    return view.getResources().getString(R.string.kislev);
                case 10:
                    return view.getResources().getString(R.string.tevet);
                case 11:
                    return view.getResources().getString(R.string.shevat);
                case 12:
                    return view.getResources().getString(R.string.adar);
                default:
                    return "Invalid month";
            }
        }

    }
}
