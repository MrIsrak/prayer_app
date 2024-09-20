package app.dll.test;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class PreferencesFuncs {
    public static void saveName(String name, SharedPreferences usernamePrefs){
        SharedPreferences.Editor editor = usernamePrefs.edit();
        editor.putString("username", name); // Ensure the key is "username"
        editor.apply();

    }

    public static void loginState(SharedPreferences sharedPreferences){
        // Save the login state
        SharedPreferences.Editor editorLogin = sharedPreferences.edit();
        editorLogin.putBoolean("isLoggedIn", true);
        editorLogin.apply();
    }
}
