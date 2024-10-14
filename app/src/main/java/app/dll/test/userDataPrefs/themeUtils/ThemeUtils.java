package app.dll.test.userDataPrefs.themeUtils;

import static android.app.PendingIntent.getActivity;

import static app.dll.test.EntranceActivity.themePrefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import app.dll.test.R;

public class ThemeUtils {
    //Getting the current theme
    // Method to check if the system theme is dark (Android 10+)
    public static boolean isSystemThemeDark(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int nightModeFlags = context.getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
        } else {
            return false;
        }
    }

    // Method to apply the theme based on user selection
    public static void setTheme(Context context) {
        String selectedTheme = themePrefs.getString("themePrefs", "light");  // Default to "light" if not set
        //TODO: FIX theme bug
        if (selectedTheme.equals("light")) {
            context.setTheme(R.style.Theme_Sidur_Dark);
        } else if (selectedTheme.equals("dark")) {
            context.setTheme(R.style.Theme_Sidur_Light);
        } else if (selectedTheme.equals("default")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                int nightModeFlags = context.getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK;
                if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                    context.setTheme(R.style.Theme_Sidur_Dark);
                } else {
                    context.setTheme(R.style.Theme_Sidur_Light);
                }
            } else {
                // Fallback to light theme for devices below Android 10
                context.setTheme(R.style.Theme_Sidur_Light);
            }
        }
        // Recreate the activity to apply the new theme
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.recreate();  // Now you can call recreate()
        }
    }
}