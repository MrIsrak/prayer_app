package app.dll.test.dateTime;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import app.dll.test.R;

public class GetDate {
    public static int today;
    public static List<String> days = new ArrayList<>();
    public static void getToday(){
        Calendar calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getTodayString(View view){
        return view.getResources().getString(R.string.today);
    }
    public static String getDayString(int today, Context context) {
        switch (today) {
            case 1: return context.getString(R.string.sunday);
            case 2: return context.getString(R.string.monday);
            case 3: return context.getString(R.string.tuesday);
            case 4: return context.getString(R.string.wednesday);
            case 5: return context.getString(R.string.thursday);
            case 6: return context.getString(R.string.friday);
            case 7: return context.getString(R.string.shabat);
            default:
                throw new IllegalArgumentException("Invalid day: " + today);
        }
    }
    public static int getDatDayInt(String day, Context context) {

        if(Objects.equals(context.getResources().getString(R.string.sunday), day)){return 1;}
        else if(Objects.equals(context.getResources().getString(R.string.monday), day)){return 2;}
        else if(Objects.equals(context.getResources().getString(R.string.tuesday), day)){return 3;}
        else if(Objects.equals(context.getResources().getString(R.string.wednesday), day)){return 4;}
        else if(Objects.equals(context.getResources().getString(R.string.thursday), day)){return 5;}
        else if(Objects.equals(context.getResources().getString(R.string.friday), day)){return 6;}
        else if(Objects.equals(context.getResources().getString(R.string.shabat), day)){return 7;}
        else if(Objects.equals(context.getResources().getString(R.string.today), day)){
            getToday();
            return today;
        }
        else{throw new IllegalArgumentException("Invalid day: " + day);}

    }

    public static String daySwitch(String day, Context context, boolean fvrd) {
        List<String> days = Arrays.asList(context.getResources().getString(R.string.sunday),
                context.getResources().getString(R.string.monday),
                context.getResources().getString(R.string.tuesday),
                context.getResources().getString(R.string.wednesday),
                context.getResources().getString(R.string.thursday),
                context.getResources().getString(R.string.friday),
                context.getResources().getString(R.string.shabat));
        getToday(); // Ensure we get the current day.

        // Replace the current day with "Today".
        String todayString = getDayString(today, context);
        List<String> updatedDays = new ArrayList<>(days); // Copy list to avoid modifying the original.
        updatedDays.set(days.indexOf(todayString), context.getResources().getString(R.string.today));

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
