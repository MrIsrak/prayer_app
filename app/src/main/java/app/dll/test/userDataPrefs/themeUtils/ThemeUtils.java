package app.dll.test.userDataPrefs.themeUtils;

import static android.app.PendingIntent.getActivity;

import static app.dll.test.EntranceActivity.themePrefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import app.dll.test.R;

public class ThemeUtils {

    // Method to check if the system theme is dark (Android 10+)
    public static boolean isSystemThemeDark(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int nightModeFlags = context.getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            return nightModeFlags == Configuration.UI_MODE_NIGHT_YES; // Return true if system is dark
        }
        return false; // Default to false for older versions
    }

    // Method to apply the theme based on user selection
    public static void setTheme(Context context) {
        String selectedTheme = themePrefs.getString("themePrefs", "light"); // Default to "light" if not set

        // Set the theme based on user selection
        switch (selectedTheme) {
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // For dark mode
                context.setTheme(R.style.Theme_Sidur_Light);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // For dark mode
                context.setTheme(R.style.Theme_Sidur_Dark);
                break;
            case "default":
                // If the user chose "default", apply the system theme logic
                if (isSystemThemeDark(context)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // For dark mode
                    context.setTheme(R.style.Theme_Sidur_Dark); // Apply dark if system is dark
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // For dark mode
                    context.setTheme(R.style.Theme_Sidur_Light); // Apply light if system is light
                }
                break;
            default:
                context.setTheme(R.style.Theme_Sidur_Light); // Fallback to light theme
                break;
        }

        // Recreate the activity to apply the new theme
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.recreate(); // Refresh the activity to apply the new theme
        }
    }
}