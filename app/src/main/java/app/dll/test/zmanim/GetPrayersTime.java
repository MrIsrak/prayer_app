package app.dll.test.zmanim;

import static app.dll.test.userDataPrefs.userLocationData.GetLocation.getLocationName;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.latitude;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.longitude;

import android.app.Activity;
import android.util.Log;

import com.kosherjava.zmanim.ComplexZmanimCalendar;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.util.GeoLocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GetPrayersTime {
    public static String alosHashachar;
    public static String netzHaChamah;
    public static String sofZmanKriatShema;
    public static String sofZmanTefillah;
    public static String chatzot;
    public static String minchaGedolah;
    public static String minchaKetanah;
    public static String plagHaMincha;
    public static String shkiah;
    public static String tzeitHaKochavim;
    public static String location;
    //For today
    public static void getPrayersTime(Activity context){

        // Formatter for displaying times
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        // Get Zmanim and print them
        ZmanimCalendar zmanimCalendar = new ZmanimCalendar();
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
    public static void getPrayersTime(Activity context, String day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String date = day.split(" ")[0];
        date = date.replace('.', '-');
        int year = calendar.get(Calendar.YEAR);
        date = year + "-" + date;
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        // Formatter for displaying times
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        // Get Zmanim and print them
        ZmanimCalendar zmanimCalendar = new ZmanimCalendar();
        zmanimCalendar.getCalendar().setTime(date1);

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
