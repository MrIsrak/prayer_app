package app.dll.test;

import android.content.SharedPreferences;

public class PreferencesFuncs {
    public static void saveName(String name, SharedPreferences usernamePrefs){
        SharedPreferences.Editor editor = usernamePrefs.edit();
        editor.putString("username", name); // Ensure the key is "username"
        editor.apply();

    }
}
