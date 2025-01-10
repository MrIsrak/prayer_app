package app.dll.test.userDataPrefs.userLocationData;

import static android.content.Context.MODE_PRIVATE;
import static app.dll.test.EntranceActivity.LOCATION_PERMISSION_REQUEST_CODE;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Toast;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class LocationPermissons {

    static public void getLocationPermission(Activity activity) {
        // Check if precise or approximate location permission is granted
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Request both precise and approximate location permissions
            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, update the location preference
            PreferencesFuncs.locState(true, activity);
            Toast.makeText(activity, "Location access already granted", Toast.LENGTH_SHORT).show();
            updateLocState(activity);  // Update the state immediately
        }
    }


    public static void updateLocState(Context context) {
        SharedPreferences locationPrefs = context.getSharedPreferences("locationPrefs", MODE_PRIVATE);
        locationPrefs.getBoolean("locationPrefs", false);
    }
}
