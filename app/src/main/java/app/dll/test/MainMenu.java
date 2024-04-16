package app.dll.test; // Consider a more descriptive package name

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);

            Button timetable_btn = findViewById(R.id.time_table_btn);

            timetable_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(new Timetable_fragment()); // Assuming the fragment class is TimetableFragment
                }
            });
        }

        private void loadFragment(Fragment fragment) {
            // Get the FragmentManager (assuming you've updated imports to androidx)
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Begin a FragmentTransaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Replace the FrameLayout with the new Fragment
            fragmentTransaction.replace(R.id.layout_main, fragment);

            // Commit the transaction
            fragmentTransaction.commit();
        }

}



