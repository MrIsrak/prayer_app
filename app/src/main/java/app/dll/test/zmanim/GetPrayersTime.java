package app.dll.test.zmanim;

import static app.dll.test.userDataPrefs.userLocationData.GetLocation.getLocationName;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.latitude;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.longitude;

import android.app.Activity;
import android.util.Log;

import com.kosherjava.zmanim.ComplexZmanimCalendar;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.util.GeoLocation;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class GetPrayersTime {
    public static void getPrayersTime(Activity context){

        // Formatter for displaying times
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Get Zmanim and print them
        ZmanimCalendar zmanimCalendar = new ZmanimCalendar();
        Log.d("Alot HaShachar (Dawn): ", timeFormat.format(zmanimCalendar.getAlosHashachar()));
        Log.d("Netz HaChamah (Sunrise): ", timeFormat.format(zmanimCalendar.getSunrise()));
        Log.d("Sof Zman Kriat Shema: ", timeFormat.format(zmanimCalendar.getSofZmanShmaGRA()));
        Log.d("Sof Zman Tefillah: ", timeFormat.format(zmanimCalendar.getSofZmanTfilaGRA()));
        Log.d("Chatzot (Midday): ", timeFormat.format(zmanimCalendar.getChatzos()));
        Log.d("Mincha Gedolah: ", timeFormat.format(zmanimCalendar.getMinchaGedola()));
        Log.d("Mincha Ketanah: ", timeFormat.format(zmanimCalendar.getMinchaKetana()));
        Log.d("Plag HaMincha: ", timeFormat.format(zmanimCalendar.getPlagHamincha()));
        Log.d("Shkiah (Sunset): ", timeFormat.format(zmanimCalendar.getSunset()));
        Log.d("Tzeit HaKochavim (Nightfall): ", timeFormat.format(zmanimCalendar.getTzais()));
        Log.d("Location", String.valueOf(zmanimCalendar.getGeoLocation()));
    }

}
