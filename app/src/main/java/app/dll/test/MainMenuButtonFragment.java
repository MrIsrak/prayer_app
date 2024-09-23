package app.dll.test;

//import static app.dll.test.EntranceActivity.name;

import static app.dll.test.EntranceActivity.profilePhotoUrl;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


        //Setting the actual name
        TextView greeting = view.findViewById(R.id.greeting);
        // Retrieve data
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "User");
        if (greeting != null) {
            greeting.setText("Hello, " + name + "!");
        }
        ImageView profilePhoto = view.findViewById(R.id.profile_photo);
        Glide.with(this).load(profilePhotoUrl).load(profilePhoto);

        //Setting up the main menu buttons
        Button timetableButton = view.findViewById(R.id.time_table_btn);
        Button textPrayersButton = view.findViewById(R.id.text_prayers_btn);
        Button tehilimButton = view.findViewById(R.id.tehelim_button);
        Button blessinButton = view.findViewById(R.id.blessings_button);

        NavController navController = Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment));

        timetableButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_timetable_fragment));
        textPrayersButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_textPrayers_fragment));
        tehilimButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_tehilim_fragment));
        blessinButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_blessings_fragment));

        BottomNavigationView navView = getActivity().findViewById(R.id.bottom_navigation);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top-level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.timetable_fragment, R.id.main_menu_button, R.id.tehilim_fragment)
                .build();

//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
