package app.dll.test.presentation.activity;

import static app.dll.test.presentation.utils.changeLocale.LanguageChange.changeLanguage;
import static app.dll.test.presentation.userDataPrefs.userPreferences.PreferencesFuncs.languageState;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.List;

import app.dll.test.R;
import app.dll.test.presentation.componets.TehillimViewModel;
import app.dll.test.presentation.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.presentation.userDataPrefs.userPreferences.PreferencesFuncs;
import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class SettingsActivity extends AppCompatActivity {

    public static String selectedTheme;
    private static String newLang = "";

    public TehillimViewModel tehillimViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tehillimViewModel = new ViewModelProvider(this).get(TehillimViewModel.class);

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
    @AndroidEntryPoint
    public static class SettingsFragment extends PreferenceFragmentCompat {

        // ViewModel shared with the Activity
        private TehillimViewModel tehillimViewModel;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load preferences from XML
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Get the ViewModel from the hosting Activity
            tehillimViewModel = new ViewModelProvider(requireActivity()).get(TehillimViewModel.class);

            setupThemePreference();
            setupLanguagePreference();
            setupClearDatabasePreference();
        }

        private void setupThemePreference() {
            ListPreference themePreference = findPreference("theme");
            if (themePreference == null) return;

            themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                String selectedTheme = newValue.toString();

                // Save the selected theme in SharedPreferences
                PreferencesFuncs.themeState(selectedTheme, requireContext());

                // Apply the theme immediately
                switch (selectedTheme) {
                    case "light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case "dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    default:
                        boolean isSystemDark = ThemeUtils.isSystemThemeDark(getContext());
                        AppCompatDelegate.setDefaultNightMode(isSystemDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                return true;
            });

            // Apply the current theme immediately
            themePreference.getOnPreferenceChangeListener().onPreferenceChange(
                    themePreference, themePreference.getValue());
        }

        private void setupLanguagePreference() {
            ListPreference langPreference = findPreference("lang");
            if (langPreference == null) return;

            langPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                String newLang = newValue.toString();

                // Change the app language
                changeLanguage(requireActivity(), requireContext(), newLang);

                // Save the language state
                languageState(newLang, requireActivity());

                return true;
            });

            // Apply the current language immediately
            langPreference.getOnPreferenceChangeListener().onPreferenceChange(
                    langPreference, langPreference.getValue());
        }

        private void setupClearDatabasePreference() {
            Preference clearDbPref = findPreference("clear_database");
            if (clearDbPref == null) return;

            clearDbPref.setOnPreferenceClickListener(preference -> {
                // Call the ViewModel method that triggers the ClearDbUseCase
                tehillimViewModel.clearDb();

                // Show a simple confirmation message
                Toast.makeText(requireContext(), "Local database cleared", Toast.LENGTH_SHORT).show();
                return true;
            });
        }
    }


}
