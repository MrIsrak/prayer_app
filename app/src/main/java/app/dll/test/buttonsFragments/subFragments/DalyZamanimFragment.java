package app.dll.test.buttonsFragments.subFragments;

import static app.dll.test.buttonsFragments.TimetableFragment.dates;
import static app.dll.test.dateTime.GetDate.getDatDayInt;
import static app.dll.test.dateTime.GetDate.today;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Objects;

import app.dll.test.R;
import app.dll.test.dateTime.GetDate;
import app.dll.test.zmanim.GetJewishDate;

public class DalyZamanimFragment extends Fragment {
    public static String day = "";
    public DalyZamanimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daly_zamanim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ImageButton back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> {
            // Navigate back to main_menu_button
            navController.navigate(R.id.action_dailyZmanim_to_timetable);
        });
        day = getArguments().getString("dayOfWeek");
        checkDay(view);
        setDay(view, day);
        setDate(view);


        ImageButton day_back_button = view.findViewById(R.id.day_back_btn);
        ImageButton day_frvrd_button = view.findViewById(R.id.day_fvrd_btn);
        day_back_button.setOnClickListener(v -> {
            setDay(view, GetDate.daySwitch(getDay(view), false));
            setDate(view);
        });
        day_frvrd_button.setOnClickListener(v -> {
            setDay(view, GetDate.daySwitch(getDay(view), true));
            setDate(view);
        });
    }
    private void checkDay(View view){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        GetDate.getToday();
        dayTextView.setText(day);
    }
    private void setDay(View view, String text){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        dayTextView.setText(text);
    }
    private void setDate(View view){
        TextView dateTextView = view.findViewById(R.id.date_tv);
        day = getDay(view);
        dateTextView.setText(dates.get(getDatDayInt(day)-1));
    }
    private String getDate(View view){
        TextView dateTextView = view.findViewById(R.id.date_tv);
        return (String) dateTextView.getText();
    }
    private String getDay(View view){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        return (String) dayTextView.getText();
    }
}