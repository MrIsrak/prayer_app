package app.dll.test.userDataPrefs.userNotificationsData;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import app.dll.test.R;

public class NotificationBuilder {
    public void showNotification(Context context, String title, String content, Intent intent) {
        // Create a NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.app_icon)          // Set notification icon
                .setContentTitle(title)                     // Set notification title
                .setContentText(content)                    // Set notification content
                .setPriority(NotificationCompat.PRIORITY_LOW) // Set notification priority
                .setDefaults(Notification.DEFAULT_VIBRATE)  // Enable vibration by default
                .setAutoCancel(true);                       // Auto-dismiss when clicked

        // Create a PendingIntent for the tap action
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE);

        // Set the PendingIntent for the notification tap
        builder.setContentIntent(pi);

        // Get the NotificationManager system service
        NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);

        // Ensure NotificationManager is not null before sending the notification
        if (notificationManager != null) {
            notificationManager.notify(1, builder.build()); // Use a unique ID for the notification
        }
    }

}
