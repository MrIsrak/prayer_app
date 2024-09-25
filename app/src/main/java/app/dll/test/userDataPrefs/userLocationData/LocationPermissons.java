package app.dll.test.userDataPrefs.userLocationData;

import static androidx.core.content.ContextCompat.startActivity;
import static app.dll.test.EntranceActivity.LOCATION_PERMISSION_REQUEST_CODE;
import static app.dll.test.EntranceActivity.locationPrefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Toast;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class LocationPermissons {
    
    static public void getLocationPermission(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, update the location preference
            PreferencesFuncs.locStae(locationPrefs, true);
            Toast.makeText(activity, "Location access already granted", Toast.LENGTH_SHORT).show();
            updateLocState(locationPrefs);  // Update the state immediately
        }
    }
    public static void updateLocState(SharedPreferences locationPrefs) {
        // Fetch the location state from SharedPreferences and update the loc variable
        locationPrefs.getBoolean("locationPrefs", false);
    }
//    public static void openLocationSetting(){
//
//
//    }
}
