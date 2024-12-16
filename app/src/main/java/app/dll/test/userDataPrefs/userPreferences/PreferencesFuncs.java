package app.dll.test.userDataPrefs.userPreferences;

import static app.dll.test.EntranceActivity.isLogin;
import static app.dll.test.EntranceActivity.locationPrefs;
import static app.dll.test.EntranceActivity.notificationPrefs;
import static app.dll.test.EntranceActivity.themePrefs;
import static app.dll.test.EntranceActivity.userName;
import static app.dll.test.userDataPrefs.userLocationData.GetLocation.coordinatesPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import app.dll.test.EntranceActivity;

public class PreferencesFuncs {
    public static void saveName(String name){
        SharedPreferences.Editor editor = userName.edit();
        editor.putString("username", name); // Ensure the key is "username"
        editor.apply();

    }
    public static void themeState(String theme){
        SharedPreferences.Editor editor = themePrefs.edit();
        editor.putString("themePrefs", theme); // Ensure the key is "theme"
        editor.apply();
    }

    public static void locState(boolean isGrained, Context context) {
        SharedPreferences.Editor locEditor = null;
        if (locationPrefs != null) {
            locEditor = locationPrefs.edit();
        } else {
            locationPrefs = context.getSharedPreferences("locationPrefs", Context.MODE_PRIVATE);
            locEditor = locationPrefs.edit();
        }

        locEditor.putBoolean("locationPrefs", isGrained);
        locEditor.apply();
    }

    public static void notState(boolean isGrained){
        SharedPreferences.Editor notificationEditor = notificationPrefs.edit();
        notificationEditor.putBoolean("notificationPrefs", isGrained);
        notificationEditor.apply();
    }
    public static void loginSate() {
        SharedPreferences.Editor editor = isLogin.edit();
        editor.putBoolean("IsLogin", true);  // Save login state
        editor.apply();
    }
}
