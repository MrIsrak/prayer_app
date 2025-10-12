package app.dll.test.presentation.utils.dateUtils;

import android.view.View;


import com.kosherjava.zmanim.hebrewcalendar.HebrewDateFormatter;
import com.kosherjava.zmanim.hebrewcalendar.JewishCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;

import java.util.GregorianCalendar;

import app.dll.test.R;

public class GetJewishDate {

    public static int getDaysInJewishMonth(JewishDate jewishDate){return jewishDate.getDaysInJewishMonth();}
    // - 1, because JewishDate starts from 1
    public static int getDayOfJewishMonth(JewishDate jewishDate){return jewishDate.getJewishDayOfMonth() - 1;}


    public static String getJewishDayAndMonth(int  year, int month, int day, View view) {
        // Create HebrewDateFormatter to get formatted Jewish dates
        HebrewDateFormatter hebrewDateFormatter = new HebrewDateFormatter();
        hebrewDateFormatter.setHebrewFormat(true); // Set to true for Hebrew characters

        // Get Jewish date and calendar
        JewishDate jewishDate = new JewishDate(new GregorianCalendar(year, month, day));


        // Get the day of the month
        int dayOfMonth = jewishDate.getJewishDayOfMonth();

        // Get the month of the year
        int monthOfYear = jewishDate.getJewishMonth();

        int jewishYear = jewishDate.getJewishYear();

        //return statement
        return String.valueOf(dayOfMonth) + " " + getJewishMonthName(monthOfYear, jewishYear, view);
    }
    public static String getJewishMonthName(int month, int year, View view) {
        JewishCalendar jewishCalendar = new JewishCalendar();
        jewishCalendar.setJewishYear(year); // -1
        if (jewishCalendar.isJewishLeapYear()) {
            return switch (month) {
                case 1 -> view.getResources().getString(R.string.nisan);
                case 2 -> view.getResources().getString(R.string.iyar);
                case 3 -> view.getResources().getString(R.string.sivan);
                case 4 -> view.getResources().getString(R.string.tamuz);
                case 5 -> view.getResources().getString(R.string.av);
                case 6 -> view.getResources().getString(R.string.elul);
                case 7 -> view.getResources().getString(R.string.tishrei);
                case 8 -> view.getResources().getString(R.string.heshvan);
                case 9 -> view.getResources().getString(R.string.kislev);
                case 10 -> view.getResources().getString(R.string.tevet);
                case 11 -> view.getResources().getString(R.string.shevat);
                case 12 -> view.getResources().getString(R.string.adar);
                case 13 -> view.getResources().getString(R.string.adar2); // Only in leap years
                default -> "Invalid month";
            };
        } else {
            return switch (month) {
                case 1 -> view.getResources().getString(R.string.nisan);
                case 2 -> view.getResources().getString(R.string.iyar);
                case 3 -> view.getResources().getString(R.string.sivan);
                case 4 -> view.getResources().getString(R.string.tamuz);
                case 5 -> view.getResources().getString(R.string.av);
                case 6 -> view.getResources().getString(R.string.elul);
                case 7 -> view.getResources().getString(R.string.tishrei);
                case 8 -> view.getResources().getString(R.string.heshvan);
                case 9 -> view.getResources().getString(R.string.kislev);
                case 10 -> view.getResources().getString(R.string.tevet);
                case 11 -> view.getResources().getString(R.string.shevat);
                case 12 -> view.getResources().getString(R.string.adar);
                default -> "Invalid month";
            };
        }

    }

    public static String getGregorianDateAndMonth(JewishCalendar jewishCalendar, int year, int month, int day) {
        // Create a temporary copy to avoid mutating the original calendar
        JewishCalendar temp = new JewishCalendar();
        temp.setJewishDate(year, month, day);

        // Get Gregorian day, month, and year
        int gDay = temp.getGregorianDayOfMonth();
        int gMonth = temp.getGregorianMonth() + 1; // KosherJava months are 0-based


        // Format as dd.MM.yyyy (e.g., 05.10.2025)
        return String.format("%02d.%02d", gDay, gMonth);
    }

}
