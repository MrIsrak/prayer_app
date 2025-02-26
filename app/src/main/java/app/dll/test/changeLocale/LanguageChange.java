package app.dll.test.changeLocale;

import static android.content.Intent.getIntent;
//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;
import static androidx.core.app.ActivityCompat.recreate;
import static androidx.core.content.ContextCompat.startActivity;

import static app.dll.test.specialUtils.SpecialUtils.*;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;

public class LanguageChange extends ContextWrapper {
    public LanguageChange(Context base) {
        super(base);
    }
    public static ContextWrapper changeLanguage(Activity activity, Context context, String languageCode) {
        Locale newLocale = new Locale(languageCode);
        Locale.setDefault(newLocale);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(newLocale);

        context = context.createConfigurationContext(config);
        return new ContextWrapper(context);
    }
}

