package app.dll.test.zmanim;

import static android.provider.Settings.System.getString;

import android.util.Log;

import com.google.protobuf.StringValue;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.HebrewDateFormatter;
import com.kosherjava.zmanim.hebrewcalendar.JewishCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetJewishDate {
    public static String getJewishDayAndMonth(int  year, int month, int day) {
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
        return String.valueOf(dayOfMonth) + " " + getJewishMonthName(monthOfYear, jewishYear);
    }
    private static String getJewishMonthName(int month, int year) {
        JewishCalendar jewishCalendar = new JewishCalendar();
        jewishCalendar.setJewishYear(year - 1);
        if (jewishCalendar.isJewishLeapYear()) {
            switch (month) {
                case 1:
                    return "Nisan";
                case 2:
                    return "Iyar";
                case 3:
                    return "Sivan";
                case 4:
                    return "Tammuz";
                case 5:
                    return "Av";
                case 6:
                    return "Elul";
                case 7:
                    return "Tishrei";
                case 8:
                    return "Cheshvan";
                case 9:
                    return "Kislev";
                case 10:
                    return "Tevet";
                case 11:
                    return "Shevat";
                case 12:
                    return "Adar I";
                case 13:
                    return "Adar II"; // Only in leap years
                default:
                    return "Invalid month";
            }
        } else {
            switch (month) {
                case 1:
                    return "Nisan";
                case 2:
                    return "Iyar";
                case 3:
                    return "Sivan";
                case 4:
                    return "Tammuz";
                case 5:
                    return "Av";
                case 6:
                    return "Elul";
                case 7:
                    return "Tishrei";
                case 8:
                    return "Cheshvan";
                case 9:
                    return "Kislev";
                case 10:
                    return "Tevet";
                case 11:
                    return "Shevat";
                case 12:
                    return "Adar I";
                default:
                    return "Invalid month";
            }
        }

    }
}
