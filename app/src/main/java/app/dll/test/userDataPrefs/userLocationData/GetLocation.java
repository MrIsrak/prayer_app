package app.dll.test.userDataPrefs.userLocationData;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.kosherjava.zmanim.ComplexZmanimCalendar;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.util.GeoLocation;
import java.text.SimpleDateFormat;


import java.util.Date;

public class GetLocation implements LocationListener {

    private LocationManager locationManager;
    private Context context;

    //Location
    public static double latitude;
    public static double longitude;

    //Zmanim
    ZmanimCalendar zmanimCalendar = new ZmanimCalendar();

    private Date sunrise;
    private Date noon;
    private Date sunset;

    public GetLocation(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void requestLocationUpdates() {
        // Check if the permission is granted before requesting location updates
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Handle permission request here
            return;
        }
        // Request location updates from the GPS provider
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1000, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            // Create GeoLocation object
            GeoLocation geoLocation = new GeoLocation();

            // Create ZmanimCalendar object for current date
            ComplexZmanimCalendar zmanimCalendar = new ComplexZmanimCalendar(geoLocation);

            // Formatter for displaying times
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            // Get Zmanim and print them
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

            // Display the location
            Toast.makeText(context, "Latitude: " + latitude + " Longitude: " + longitude, Toast.LENGTH_SHORT).show();
        }
        //TODO: CHECK ZMANIM
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // You can handle status changes here if necessary
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Called when the provider (GPS or Network) is enabled
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Called when the provider is disabled
        //TODO: Notify the user about his GPS in invaluable and zamnim us not working precisely
    }
}
