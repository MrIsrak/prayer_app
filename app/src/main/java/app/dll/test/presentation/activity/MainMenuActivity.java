package app.dll.test.presentation.activity;
//import static app.dll.test.presentation.activity.EntranceActivity.themePrefs;
import static app.dll.test.presentation.activity.SettingsActivity.selectedTheme;
import static app.dll.test.presentation.utils.changeLocale.LanguageChange.changeLanguage;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import app.dll.test.R;
import app.dll.test.presentation.utils.changeLocale.LanguageChange;
import app.dll.test.presentation.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.presentation.userDataPrefs.userLocationData.GetLocation;
import app.dll.test.presentation.userDataPrefs.userPreferences.PreferencesFuncs;

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
