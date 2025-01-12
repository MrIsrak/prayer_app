package app.dll.test.userDataPrefs.userNotificationsData;

import static app.dll.test.EntranceActivity.NOTIFICATION_PERMISSION_REQUEST_CODE;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.widget.Toast;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class NotificationPermissons {

    public static void updateNotificationState(Context context) {
        SharedPreferences notificationPrefs = context.getSharedPreferences("notificationPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = notificationPrefs.edit();
        editor.putBoolean("notificationPrefs", true);
        editor.apply();
    }

    public static void getNotificationPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    NOTIFICATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, update the notification preference
            PreferencesFuncs.notState(true, activity);
            Toast.makeText(activity, "Notification access already granted", Toast.LENGTH_SHORT).show();
            updateNotificationState(activity);  // Update the state immediately
        }
    }

}
