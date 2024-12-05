package app.dll.test.dateTime;

import android.util.Log;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class GetDate {
    public static int today;
    public static List<String> days = new ArrayList<>();
    public static void getToday(){
        Calendar calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getDayString(int today){
        switch (today){
            case 1: return "Sunday";
            case 2: return "Monday";
            case 3: return "Tuesday";
            case 4: return "Wednesday";
            case 5: return "Thursday";
            case 6: return "Friday";
            case 7: return "Shabbat";
            default:
                throw new IllegalArgumentException("Invalid day: " + today);
        }
    }
    public static int getDatDayInt(String day) {
        switch (day) {
            case "Sunday":    return 1;
            case "Monday":    return 2;
            case "Tuesday":   return 3;
            case "Wednesday": return 4;
            case "Thursday":  return 5;
            case "Friday":    return 6;
            case "Shabbat":  return 7;
            case "Today":
                getToday();
                return today;
            default:
                throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    public static String daySwitch(String day, boolean fvrd) {
        List<String> days = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Shabbat");
        getToday(); // Ensure we get the current day.

        // Replace the current day with "Today".
        String todayString = getDayString(today);
        List<String> updatedDays = new ArrayList<>(days); // Copy list to avoid modifying the original.
        updatedDays.set(days.indexOf(todayString), "Today");

        // Determine the next or previous day based on the boolean flag `fvrd`.
        int index = updatedDays.indexOf(day);
        String resultDay = "";

        if (fvrd) {
            // Move forward in the list.
            resultDay = index + 1 == updatedDays.size() ? updatedDays.get(0) : updatedDays.get(index + 1);
        } else {
            // Move backward in the list.
            resultDay = index - 1 < 0 ? updatedDays.get(updatedDays.size() - 1) : updatedDays.get(index - 1);
        }

        return resultDay;
    }
}
