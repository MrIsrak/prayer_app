package app.dll.test.userDataPrefs.userPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import app.dll.test.EntranceActivity;

public class PreferencesFuncs {
    public static void saveName(String name, Context context){
        SharedPreferences userName = context.getSharedPreferences("userName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userName.edit();
        editor.putString("username", name); // Ensure the key is "username"
        editor.apply();
    }
    public static void themeState(String theme, Context context){
        SharedPreferences themePrefs = context.getSharedPreferences("themePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = themePrefs.edit();
        editor.putString("themePrefs", theme); // Ensure the key is "theme"
        editor.apply();
    }
    public static void languageState(String lang, Context context){
        SharedPreferences langPrefs = context.getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = langPrefs.edit();
        editor.putString("langPrefs", lang); // Ensure the key is "lang"
        editor.apply();
    }

    public static void locState(boolean isGrained, Context context) {
        SharedPreferences locationPrefs = context.getSharedPreferences("locationPrefs", Context.MODE_PRIVATE);
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

    public static void notState(boolean isGrained, Context context){
        SharedPreferences notificationPrefs = context.getSharedPreferences("notificationPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor notificationEditor = notificationPrefs.edit();
        notificationEditor.putBoolean("notificationPrefs", isGrained);
        notificationEditor.apply();
    }
    public static void loginSate(Context context) {
        SharedPreferences isLogin = context.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = isLogin.edit();
        editor.putBoolean("IsLogin", true);  // Save login state
        editor.apply();
    }

    public static String getLanguage(Context context){
        SharedPreferences langPrefs = context.getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
        return langPrefs.getString("langPrefs", "en");
    }
}
