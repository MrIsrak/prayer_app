package app.dll.test.presentation.utils.dateUtils;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import app.dll.test.R;

public class GetDate {

    // Get the current day of the week (1–7)
    public static int getToday(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static int getToday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }


    // Returns the localized "Today" string
    public static String getTodayString(Context context) {
        return context.getString(R.string.today);
    }

    // Converts a day number (1–7) into a localized day name
    public static String getDayString(int day, Context context) {
        return switch (day) {
            case Calendar.SUNDAY -> context.getString(R.string.sunday);
            case Calendar.MONDAY -> context.getString(R.string.monday);
            case Calendar.TUESDAY -> context.getString(R.string.tuesday);
            case Calendar.WEDNESDAY -> context.getString(R.string.wednesday);
            case Calendar.THURSDAY -> context.getString(R.string.thursday);
            case Calendar.FRIDAY -> context.getString(R.string.friday);
            case Calendar.SATURDAY -> context.getString(R.string.shabat);
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
    }

    // Converts a localized day name back to its numeric value (1–7)
    public static int getDayInt(String day, Context context) {
        if (Objects.equals(context.getString(R.string.today), day))
            return getToday();
        if (Objects.equals(context.getString(R.string.sunday), day)) return Calendar.SUNDAY;
        if (Objects.equals(context.getString(R.string.monday), day)) return Calendar.MONDAY;
        if (Objects.equals(context.getString(R.string.tuesday), day)) return Calendar.TUESDAY;
        if (Objects.equals(context.getString(R.string.wednesday), day)) return Calendar.WEDNESDAY;
        if (Objects.equals(context.getString(R.string.thursday), day)) return Calendar.THURSDAY;
        if (Objects.equals(context.getString(R.string.friday), day)) return Calendar.FRIDAY;
        if (Objects.equals(context.getString(R.string.shabat), day)) return Calendar.SATURDAY;

        throw new IllegalArgumentException("Invalid day: " + day);
    }

    // Returns a list of week day names
    // If `replaceToday` is true, the current day will be replaced with "Today"
    public static List<String> getDaysList(Context context, boolean replaceToday) {
        List<String> days = new ArrayList<>(Arrays.asList(
                context.getString(R.string.sunday),
                context.getString(R.string.monday),
                context.getString(R.string.tuesday),
                context.getString(R.string.wednesday),
                context.getString(R.string.thursday),
                context.getString(R.string.friday),
                context.getString(R.string.shabat)
        ));

        if (replaceToday) {
            int todayIndex = getToday() - 1; // Calendar.SUNDAY = 1
            days.set(todayIndex, context.getString(R.string.today));
        }

        return days;
    }

    // Switches to the next or previous day in the week
    // If `forward` = true → move forward; if false → move backward
    public static String daySwitch(String currentDay, Context context, boolean forward) {
        List<String> days = getDaysList(context, true);
        int index = days.indexOf(currentDay);
        if (index == -1) return currentDay;

        if (forward)
            return days.get((index + 1) % days.size());
        else
            return days.get((index - 1 + days.size()) % days.size());
    }

    // --- Calendar helper methods ---

    // Returns the month number (1–12)
    // Plus 1 because Calendar.MONTH starts from 0
    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    // Returns the number of days in the given month
    public static int getDaysInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // Returns the current year
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }
}
