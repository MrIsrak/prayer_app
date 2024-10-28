package app.dll.test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.userDataPrefs.userLocationData.GetLocation;
import app.dll.test.zmanim.GetJewishDate;

public class MainMenuActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Getting user location
        GetLocation getLocation = new GetLocation(this, this);
        getLocation.requestLocationUpdates();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SETTINGS && resultCode == RESULT_OK) {
            // Theme has been changed, recreate the activity to apply the new theme
            ThemeUtils.setTheme(this);

        }
    }
}
