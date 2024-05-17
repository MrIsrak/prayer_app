package app.dll.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Main_menu_button_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {return inflater.inflate(R.layout.fragment_main_menu_button, container, false);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button timetableButton = view.findViewById(R.id.time_table_btn);
        Button textPrayersButton = view.findViewById(R.id.text_prayers_btn);
        Button tehilimButton = view.findViewById(R.id.tehelim_button);
        Button blessinButton = view.findViewById(R.id.blessings_button);



        NavController navController = Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment));

//TODO: After switching the fragment previous one does not hide!!
        timetableButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_timetable_fragment));
        textPrayersButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_textPrayers_fragment));
        tehilimButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_tehilim_fragment));
        blessinButton.setOnClickListener(v -> navController.navigate(R.id.action_main_menu_button_to_blessings_fragment));

//        time_table.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {Navigation.findNavController(view).navigate(R.id.action_main_menu_button_to_timetable_fragment);}
//
//        });

//        text_prayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { replaceFragment(new TextPrayers_fragment());}
//        });

//        tehilim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {replaceFragment(new Tehilim_fragment());}
//        });

//        blessings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {replaceFragment(new Blessings_fragment());}
//        });


//    public void replaceFragment(Fragment fragment){
//        //replace the fragment with actual class name
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        Fragment timetableFragment = new Timetable_fragment();
//        transaction.replace(R.id.menu_buttons, timetableFragment);
//        transaction.commit();
//    }
    }

}