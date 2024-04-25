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
        View v = inflater.inflate(R.layout.fragment_main_menu_button, container, false);

        Button time_table = v.findViewById(R.id.time_table_btn);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        time_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment timetableFragment = new Timetable_fragment();
                transaction.replace(R.id.menu_buttons, timetableFragment);
                transaction.commit();
            }
        });

        return v;


    }



}