package app.dll.test.dateTime;

import java.time.LocalDate;
import java.util.Calendar;

public class GetDate {
    public static int today;
    public static void getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        today = calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getTodayString(){
        String todayString = "";
        getToday();
        switch (today){
            case 1:
                todayString = "Sunday";
                break;
            case 2:
                todayString = "Monday";
                break;
            case 3:
                todayString = "Tuesday";
                break;
            case 4:
                todayString = "Wednesday";
                break;
            case 5:
                todayString = "Thursday";
                break;
            case 6:
                todayString = "Friday";
                break;
            case 7:
                todayString = "Saturday";
                break;
        }
        return todayString;
    }
}
