package app.dll.test.userDataPrefs.userLocationData;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.kosherjava.zmanim.ComplexZmanimCalendar;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.util.GeoLocation;

import java.io.IOException;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executor;

public class GetLocation implements LocationListener {
    private LocationManager locationManager;
    private Context context;
    public static FusedLocationProviderClient fusedLocationClient;
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000000000, 1000, this);
    }
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            String locationName = getLocationName(context);
            GeoLocation geoLocation = new GeoLocation(getLocationName(context), latitude, longitude, TimeZone.getTimeZone(locationName));
            zmanimCalendar = new ComplexZmanimCalendar(geoLocation);
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //If location changed, update it
        getLastLocation((Activity) context);
    }
    @Override
    public void onProviderEnabled(String provider) {
        // Called when the provider (GPS or Network) is enabled
        //TODO:Send notification that zmanim by location is available
    }
    @Override
    public void onProviderDisabled(String provider) {
        // Called when the provider is disabled
        //TODO: Notify the user about his GPS in invaluable and zamnim us not working precisely
    }
    //Get the location name
    public static String getLocationName(Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            // Get the address list (contains possible matches for the given latitude and longitude)
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);  // Get the first address in the list
                return address.getLocality();        // Returns the city name
            }
        } catch (IOException e) {
            Log.e("Location not found", e.getMessage());
        }
        return "Location not found";
    }
    public static void getLastLocation(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        Task<Location> locationResult = fusedLocationClient.getLastLocation();
        locationResult.addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Use the location here
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        });
    }

}
