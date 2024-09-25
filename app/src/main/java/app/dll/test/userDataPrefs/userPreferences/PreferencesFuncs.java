package app.dll.test.userDataPrefs.userPreferences;

import static app.dll.test.EntranceActivity.isLogin;
import static app.dll.test.EntranceActivity.userName;

import android.content.SharedPreferences;

import app.dll.test.EntranceActivity;

public class PreferencesFuncs {
    public static void saveName(String name){
        SharedPreferences.Editor editor = userName.edit();
        editor.putString("username", name); // Ensure the key is "username"
        editor.apply();

    }

    public static void loginState(SharedPreferences sharedPreferences){
        // Save the login state
        SharedPreferences.Editor editorLogin = sharedPreferences.edit();
        editorLogin.putBoolean("isLoggedIn", true);
        editorLogin.apply();
    }

    public static void locStae(SharedPreferences locationPrefs, boolean idGraind){
        SharedPreferences.Editor locEditor = locationPrefs.edit();
        locEditor.putBoolean("locationPrefs", idGraind);
        locEditor.apply();
    }
    public static void loginSate() {
        SharedPreferences.Editor editor = EntranceActivity.isLogin.edit();
        editor.putBoolean("IsLogin", true);  // Save login state
        editor.apply();
    }

}
