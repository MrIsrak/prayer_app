package app.dll.test.userDataPrefs.userPreferences;

import static app.dll.test.EntranceActivity.isLogin;
import static app.dll.test.EntranceActivity.locationPrefs;
import static app.dll.test.EntranceActivity.notificationPrefs;
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

    public static void locState( boolean isGrained){
        SharedPreferences.Editor locEditor = locationPrefs.edit();
        locEditor.putBoolean("locationPrefs", isGrained);
        locEditor.apply();
    }

    public static void notState(boolean isGrained){
        SharedPreferences.Editor notificationEditor = notificationPrefs.edit();
        notificationEditor.putBoolean("notificationPrefs", isGrained);
        notificationEditor.apply();
    }
    public static void loginSate() {
        SharedPreferences.Editor editor = EntranceActivity.isLogin.edit();
        editor.putBoolean("IsLogin", true);  // Save login state
        editor.apply();
    }

}
