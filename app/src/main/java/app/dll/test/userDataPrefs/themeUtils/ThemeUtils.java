package app.dll.test.userDataPrefs.themeUtils;

import static android.app.PendingIntent.getActivity;

import static androidx.core.app.ActivityCompat.recreate;
import static app.dll.test.EntranceActivity.themePrefs;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import app.dll.test.R;

public class ThemeUtils {

    // Method to check if the system theme is dark (Android 10+)
    public static boolean isSystemThemeDark(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
            if (uiModeManager != null) {
                int nightMode = uiModeManager.getNightMode();
                if (nightMode == UiModeManager.MODE_NIGHT_YES) {
                    return true; // System theme is dark
                }
            }
        }
        return false; // Default to light theme for older versions or if system is not in dark mode
    }

    // Method to apply the theme based on user selection
    public static void setTheme(Context context) {
        String selectedTheme = themePrefs.getString("themePrefs", "light"); // Default to "light" if not set

        // Set the theme based on user selection
        switch (selectedTheme) {
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Dark off
//                context.setTheme(R.style.Theme_Sidur_Light);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Dark on
//                context.setTheme(R.style.Theme_Sidur_Dark);
                break;
            case "default":
                // If the user chose "default", apply the system theme logic
                if (isSystemThemeDark(context)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // For dark mode
//                    context.setTheme(R.style.Theme_Sidur_Dark); // Apply dark if system is dark
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // For dark mode
//                    context.setTheme(R.style.Theme_Sidur_Light); // Apply light if system is light
                }
                break;

        }
        // Recreate activity only if the context is an Activity
        if (context instanceof Activity) {
            ((Activity) context).recreate();
        } else {
            Log.w("ThemeUtils", "Context is not an instance of Activity; recreate() skipped.");
        }
    }
}