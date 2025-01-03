package app.dll.test;
import static app.dll.test.EntranceActivity.themePrefs;
import static app.dll.test.SettingsActivity.selectedTheme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.userDataPrefs.userLocationData.GetLocation;
import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;
import app.dll.test.zmanim.GetJewishDate;
public class MainMenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SETTINGS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        String currentTheme = themePrefs.getString("themePrefs", "light");

        ThemeUtils.setTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Getting user location
        GetLocation getLocation = new GetLocation(this, this);
        getLocation.requestLocationUpdates();
    }
    @Override
    protected void onResume() {
        String currentTheme = themePrefs.getString("themePrefs", "light");
        super.onResume();
        if (!currentTheme.equals(selectedTheme)) {
            // Apply the new theme
            ThemeUtils.setTheme(this);
        }
    }
}
