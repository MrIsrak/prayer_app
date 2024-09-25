package app.dll.test.userDataPrefs.userLocationData;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class GetLocation implements LocationListener {

    private LocationManager locationManager;
    private Context context;

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
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // Display the location
            Toast.makeText(context, "Latitude: " + latitude + " Longitude: " + longitude, Toast.LENGTH_SHORT).show();
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
    }
}
