package app.dll.test.userDataPrefs.userNotificationsData;

import static androidx.core.content.ContextCompat.getSystemService;

import static app.dll.test.EntranceActivity.NOTIFICATION_PERMISSION_REQUEST_CODE;
import static app.dll.test.EntranceActivity.notificationPrefs;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

public class NotificationPermisson {

    public static void updateNotificationState() {
        SharedPreferences.Editor editor = notificationPrefs.edit();
        editor.putBoolean("notificationPrefs", true);
        editor.apply();
    }

    public static void requestNotificationPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,

            new String[]{Manifest.permission.POST_NOTIFICATIONS},

            NOTIFICATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, proceed
            updateNotificationState();
            // Your code to send notifications or perform actions requiring permission
        }
    }

    public static void crateNotificationChanel(Activity context){
        // Only create the notification channel on Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
}
