package app.dll.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Main_menu_button extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu_button, container, false);

        Button time_table = view.findViewById(R.id.time_table_btn);
        Button text_prayers = view.findViewById(R.id.text_prayers_btn);
        Button tehelim = view.findViewById(R.id.tehelim_button);
        Button blessings = view.findViewById(R.id.blessings_button);





        time_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Timetable_fragment());
            }
        });

        text_prayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { replaceFragment(new TextPrayers_fragment());}
        });

        tehelim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {replaceFragment(new TehilimFragment());}
        });

        blessings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {replaceFragment(new BlessingsFragment());}
        });


        return view;


    }

    public void replaceFragment(Fragment fragment){
        //replace the fragment with actual class name
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment timetableFragment = new Timetable_fragment();
        transaction.replace(R.id.menu_buttons, timetableFragment);
        transaction.commit();
    }

}