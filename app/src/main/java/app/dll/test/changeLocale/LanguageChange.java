package app.dll.test.changeLocale;

import static android.content.Intent.getIntent;
import static androidx.core.app.ActivityCompat.recreate;
import static androidx.core.content.ContextCompat.startActivity;

import static app.dll.test.specialUtils.SpecialUtils.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class LanguageChange {
    public static void changeLanguage(Context context, String languageCode) {
        PreferencesFuncs.languageState(languageCode, context);
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        context.createConfigurationContext(config);
        // Apply the changes immediately
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        context.getApplicationContext().getResources().updateConfiguration(
                context.getResources().getConfiguration(),
                context.getResources().getDisplayMetrics()
        );

        Log.d("RESTART", "RESTART");
    }

}
