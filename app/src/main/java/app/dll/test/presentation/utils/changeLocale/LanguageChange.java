package app.dll.test.presentation.utils.changeLocale;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
        import android.content.res.Configuration;
import android.content.res.Resources;

        import java.util.Locale;

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

