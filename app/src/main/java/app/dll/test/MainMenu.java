package app.dll.test; // Consider a more descriptive package name

import static androidx.fragment.app.FragmentManagerKt.commit;

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

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            Fragment main_buttons = new Fragment(); // Replace "YourFragment" with your actual class name
            fragmentTransaction.add(R.id.menu_buttons, main_buttons);  // Replace with your container's ID
            fragmentTransaction.commit();



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



