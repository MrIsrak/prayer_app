package app.dll.test;

import dagger.hilt.android.HiltAndroidApp;
import android.app.Application;
import android.util.Log;

@HiltAndroidApp
public class Hilt_SidurApplication extends Application {
    private static final String TAG = "SidurApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application created and Hilt is initialized.");
    }
}
