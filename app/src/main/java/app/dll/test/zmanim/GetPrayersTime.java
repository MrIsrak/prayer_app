package app.dll.test.zmanim;

import static app.dll.test.userDataPrefs.userLocationData.GetLocation.getLastLocation;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.getLocationName;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.latitude;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.longitude;

import android.app.Activity;
import android.util.Log;

import com.google.android.material.timepicker.TimeFormat;
import com.kosherjava.zmanim.ComplexZmanimCalendar;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.util.GeoLocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.dll.test.userDataPrefs.userLocationData.GetLocation;

public class GetPrayersTime {
    static ZmanimCalendar zmanimCalendar = new ZmanimCalendar();
    public static String alosHashachar, netzHaChamah, sofZmanKriatShema, sofZmanTefillah,
    chatzot, minchaGedolah, minchaKetanah, plagHaMincha, shkiah, tzeitHaKochavim;

    public static String location;
    //For today
    public static void getPrayersTime(Activity context){

        // Formatter for displaying times
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        // Get Zmanim and print them

        alosHashachar = timeFormat.format(zmanimCalendar.getAlosHashachar());
        netzHaChamah = timeFormat.format(zmanimCalendar.getSunrise());
        sofZmanKriatShema = timeFormat.format(zmanimCalendar.getSofZmanShmaGRA());
        sofZmanTefillah = timeFormat.format(zmanimCalendar.getSofZmanTfilaGRA());
        chatzot = timeFormat.format(zmanimCalendar.getChatzos());
        minchaGedolah = timeFormat.format(zmanimCalendar.getMinchaGedola());
        minchaKetanah = timeFormat.format(zmanimCalendar.getMinchaKetana());
        plagHaMincha = timeFormat.format(zmanimCalendar.getPlagHamincha());
        shkiah = timeFormat.format(zmanimCalendar.getSunset());
        tzeitHaKochavim = timeFormat.format(zmanimCalendar.getTzais());
        location = String.valueOf(zmanimCalendar.getGeoLocation());
    }
    //For specific day
    public static void getPrayersTime(Activity activity, String days) throws ParseException {
        //Getting last location of user
        getLastLocation(activity);
        //Getting Calendar to format the time of prayers
        Calendar calendar = Calendar.getInstance();
        String dateNoSplit = days.split(" ")[0];
        // Split the string into day, month, and year components
        String[] parts = dateNoSplit.split("\\."); // Split by "."
        int day = Integer.parseInt(parts[0]); // Day
        int month = Integer.parseInt(parts[1]) - 1; // Month: 12 (Calendar months are 0-based)
        int year = calendar.get(Calendar.YEAR); // Year

        // Create a Calendar instance and set the date
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        //Formatting date
        Date date = calendar.getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        GeoLocation geoLocation = new GeoLocation(getLocationName(activity), latitude, longitude, TimeZone.getDefault());

        // Get Zmanim
        ComplexZmanimCalendar zmanimCalendar = new ComplexZmanimCalendar(geoLocation);
        zmanimCalendar.setCalendar(calendar);


        alosHashachar = timeFormat.format(zmanimCalendar.getAlosHashachar());
        netzHaChamah = timeFormat.format(zmanimCalendar.getSunrise());
        sofZmanKriatShema = timeFormat.format(zmanimCalendar.getSofZmanShmaGRA());
        sofZmanTefillah = timeFormat.format(zmanimCalendar.getSofZmanTfilaGRA());
        chatzot = timeFormat.format(zmanimCalendar.getChatzos());
        minchaGedolah = timeFormat.format(zmanimCalendar.getMinchaGedola());
        minchaKetanah = timeFormat.format(zmanimCalendar.getMinchaKetana());
        plagHaMincha = timeFormat.format(zmanimCalendar.getPlagHamincha());
        shkiah = timeFormat.format(zmanimCalendar.getSunset());
        tzeitHaKochavim = timeFormat.format(zmanimCalendar.getTzais());
        location = String.valueOf(zmanimCalendar.getGeoLocation());
    }
}
