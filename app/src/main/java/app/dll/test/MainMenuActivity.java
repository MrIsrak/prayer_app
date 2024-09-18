package app.dll.test; // Consider a more descriptive package name

import static app.dll.test.EntranceActivity.name;
import static app.dll.test.R.*;

import androidx.appcompat.app.AppCompatActivity;
import app.dll.test.firebase.firebaseSetUp;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }
}


