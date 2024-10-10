package app.dll.test.userDataPrefs.userNotificationsData;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class NotificationChannelCreating {
    public static void createNotificationChannel(Activity context, String id, String title, int importance, String description) {
        // Only create the notification channel on Android O (API level 26) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create a new NotificationChannel with the provided ID, name, and importance level
            NotificationChannel channel = new NotificationChannel(id, title, importance);

            // Set the description for the channel, which helps users understand what the channel is used for
            channel.setDescription(description);

            // Get the NotificationManager system service, which manages notifications
            NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);

            // Ensure the NotificationManager is not null and create the notification channel
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}
