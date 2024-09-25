package app.dll.test; // Consider a more descriptive package name

//import static app.dll.test.EntranceActivity.name;
import static app.dll.test.R.*;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import app.dll.test.userDataPrefs.userLocationData.GetLocation;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Getting user location
        GetLocation getLocation = new GetLocation(this);

        getLocation.requestLocationUpdates();

    }
}


