package app.dll.test;

//import static app.dll.test.EntranceActivity.name;

import static androidx.core.content.ContextCompat.getSystemService;
import static app.dll.test.zmanim.SpecialDetails.getTorahPortion;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import app.dll.test.userDataPrefs.userNotificationsData.NotificationChannelCreating;
import app.dll.test.userDataPrefs.userNotificationsData.NotificationPermissons;


public class MainMenuButtonFragment extends Fragment {
    private static final String CHANNEL_ID = "your_channel_id";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE_SETTINGS = 1;

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
        if (greeting != null) {greeting.setText("Hello, " + name + "!");}

//        ImageView profilePhoto = view.findViewById(R.id.profile_photo);
//        Glide.with(this).load(profilePhotoUrl).load(profilePhoto);

        //Setting up the main menu buttons
        Button timetableButton = view.findViewById(R.id.time_table_btn);
        Button textPrayersButton = view.findViewById(R.id.text_prayers_btn);
        Button tehilimButton = view.findViewById(R.id.tehelim_button);
        Button blessinButton = view.findViewById(R.id.blessings_button);
        ImageButton settingsButton = view.findViewById(R.id.settings_btn);

            NavController navController = Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment));

        timetableButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_timetable_fragment));
        textPrayersButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_textPrayers_fragment));
        tehilimButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_tehilim_fragment));
        blessinButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_blessings_fragment));
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), SettingsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SETTINGS); // Use startActivityForResult
        });
        BottomNavigationView navView = getActivity().findViewById(R.id.bottom_navigation);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top-level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.timetable_fragment, R.id.main_menu_button, R.id.tehilim_fragment)
                .build();

//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //TODO: Finish notification system

        //Creating Notification chanel for prayers notification
        NotificationChannelCreating.createNotificationChannel(requireActivity(),
                "reminder_channel",
                "Reminders",
                NotificationManager.IMPORTANCE_HIGH,
                "Channel of reminding notifications for daily prayers");

        //Torah portion
        TextView dvar_torah = view.findViewById(R.id.dvar_torah);
        if(getTorahPortion() != null){dvar_torah.setText("Weakly Torah portion is: " + getTorahPortion());}
        else{dvar_torah.setText(" " );}


    }
}
