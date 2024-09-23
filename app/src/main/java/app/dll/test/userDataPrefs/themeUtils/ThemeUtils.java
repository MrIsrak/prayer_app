package app.dll.test.userDataPrefs.themeUtils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import app.dll.test.R;

public class ThemeUtils {
    //Getting the current theme
    public static boolean isSystemThemeDark(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context.getResources().getConfiguration().uiMode == Configuration.UI_MODE_NIGHT_YES;
        } else {

            return false;
        }
    }
    //Set the device default theme
    public static void setTheme(SharedPreferences themePrefs){
        boolean isDarkTheme = themePrefs.getBoolean("isDarkTheme", false);

        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
}