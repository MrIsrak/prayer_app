package app.dll.test.userDataPrefs.userLocationData;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class GetLocation implements LocationListener {

    private LocationManager locationManager;
    private Context context;

    //Location
    public static double latitude;
    public static double longitude;

    //Zmanim
    ComplexZmanimCalendar zmanimCalendar = new ComplexZmanimCalendar();


    public GetLocation(Context context, Activity activity) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationPermissons.getLocationPermission(activity);
    }

    public void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; // Permission is not granted
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1000, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            String locationName = getLocationName(context, latitude, longitude);
            GeoLocation geoLocation = new GeoLocation("Location", latitude, longitude, TimeZone.getTimeZone(locationName));
            zmanimCalendar = new ComplexZmanimCalendar(geoLocation);
        }
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
    //Get the location name
    public static String getLocationName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            // Get the address list (contains possible matches for the given latitude and longitude)
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);  // Get the first address in the list
                return address.getLocality();        // Returns the city name
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Location not found";
    }
}
