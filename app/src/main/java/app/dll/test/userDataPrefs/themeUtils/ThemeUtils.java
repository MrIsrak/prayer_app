package app.dll.test.userDataPrefs.themeUtils;

import static android.app.PendingIntent.getActivity;

import static androidx.core.app.ActivityCompat.recreate;
import static app.dll.test.EntranceActivity.themePrefs;

import android.app.Activity;
import android.app.UiModeManager;
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


    public static void setTheme(Context context) {
        themePrefs = context.getSharedPreferences("themePrefs", Context.MODE_PRIVATE);
        String selectedTheme = themePrefs.getString("themePrefs", "light"); // Default to "light" if not set
        int newMode;

        // Determine the mode based on user selection
        switch (selectedTheme) {
            case "light":
                newMode = AppCompatDelegate.MODE_NIGHT_NO; // Light mode
                context.setTheme(R.style.Theme_Sidur_Light);
                break;
            case "dark":
                newMode = AppCompatDelegate.MODE_NIGHT_YES; // Dark mode
                context.setTheme(R.style.Theme_Sidur_Dark);
                break;
            case "default":
                if (isSystemThemeDark(context)) {
                    newMode = AppCompatDelegate.MODE_NIGHT_YES; // Follow system theme (dark)
                    context.setTheme(R.style.Theme_Sidur_Dark);
                } else {
                    newMode = AppCompatDelegate.MODE_NIGHT_NO; // Follow system theme (light)
                    context.setTheme(R.style.Theme_Sidur_Light);
                }
                break;
            default:
                newMode = AppCompatDelegate.MODE_NIGHT_NO; // Fallback to light mode
                context.setTheme(R.style.Theme_Sidur_Light);
                break;
        }

        // Apply the new mode only if it differs from the current mode
        if (AppCompatDelegate.getDefaultNightMode() != newMode) {
            AppCompatDelegate.setDefaultNightMode(newMode);
            if (context instanceof Activity) {
                ((Activity) context).recreate(); // Recreate the activity to apply the theme
            }
        }
    }

}