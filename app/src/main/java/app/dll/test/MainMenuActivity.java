package app.dll.test;
//import static app.dll.test.EntranceActivity.themePrefs;
import static app.dll.test.SettingsActivity.selectedTheme;
import static app.dll.test.changeLocale.LanguageChange.changeLanguage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ShareActionProvider;

import app.dll.test.changeLocale.LanguageChange;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.userDataPrefs.userLocationData.GetLocation;
import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;
import app.dll.test.zmanim.GetJewishDate;
public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        LanguageChange.changeLanguage(this, this, PreferencesFuncs.getLanguage(this));
        changeLanguage(this, this, PreferencesFuncs.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Getting user location
        GetLocation getLocation = new GetLocation(this, this);
        getLocation.requestLocationUpdates();
    }
    @Override
    protected void onResume() {
        SharedPreferences themePrefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        String currentTheme = themePrefs.getString("themePrefs", "light");
        super.onResume();
        if (!currentTheme.equals(selectedTheme)) {
            // Apply the new theme
            ThemeUtils.setTheme(this);
        }
        changeLanguage(this, this, PreferencesFuncs.getLanguage(this));
    }
}
