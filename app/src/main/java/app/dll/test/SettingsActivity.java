package app.dll.test;

import static app.dll.test.EntranceActivity.themePrefs;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class SettingsActivity extends AppCompatActivity {

    public static String selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.setTheme(this);
        setContentView(R.layout.settings_activity);

        // Check if savedInstanceState is null, then replace fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        // Set up the ActionBar if available
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // Inner class for handling settings in a PreferenceFragmentCompat
    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Find the theme ListPreference by its key
            ListPreference themePreference = findPreference("theme");

            if (themePreference != null) {
                themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                        selectedTheme = newValue.toString();

                        // Save the selected theme to SharedPreferences
                        PreferencesFuncs.themeState(selectedTheme);

                        // Check if the theme has actually changed before applying it
                        String currentTheme = themePrefs.getString("themePrefs", "light");
                        Log.d(currentTheme, currentTheme);

                        if (selectedTheme.equals("light"))
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        else if (selectedTheme.equals("dark"))
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        else {
                            if (ThemeUtils.isSystemThemeDark(getContext()))
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            else
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }

                        return true;  // Return true to update the state of the preference
                    }
                });
                // Call onPreferenceChange manually to apply the current value
                themePreference.getOnPreferenceChangeListener().onPreferenceChange(
                        themePreference, themePreference.getValue());
            }
        }
    }
}
