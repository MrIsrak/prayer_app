package app.dll.test.specialUtils;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SpecialUtils {
    public static void saveCurrentActivity(Class<?> activityClass, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lastActivity", activityClass.getName());
        editor.apply();
    }
    // Check if the app has already been activated once
    public static boolean isAppActivatedOnce(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getBoolean("isActivatedOnce", false);
    }

    // Set the app as activated once
    public static void setAppActivatedOnce(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isActivatedOnce", true);
        editor.apply();
    }
        public static void restartApp(Context context) {
            SharedPreferences activated = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
            if(!isAppActivatedOnce(context)){

                setAppActivatedOnce(context);

                Intent intent = new Intent(context, context.getClass());

                // Add flags to clear the activity stack and start fresh
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Start the default activity
                context.startActivity(intent);

                // Kill the current process to ensure a fresh restart
                android.os.Process.killProcess(android.os.Process.myPid());
            }

        }



}
