package app.dll.test;

//import static app.dll.test.EntranceActivity.name;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.SharedPreferences;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class MainMenuButtonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_menu_button, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView greeting = view.findViewById(R.id.greeting);

        // Retrieve data
        SharedPreferences usernamePrefs = requireActivity().getSharedPreferences("username", Context.MODE_PRIVATE);
        String name = usernamePrefs.getString("username", "default_value"); // Ensure the key is "username"

        Log.d("MainMenuButtonFragment", "Retrieved name: " + name);

        if (greeting != null) {
            greeting.setText("Hello, " + name + "!");
        }

        // Initialize buttons and navigation...
    }
}
