package app.dll.test.buttonsFragments;

import static app.dll.test.zmanim.GetJewishDate.getJewishDayAndMonth;

import android.graphics.PorterDuff;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import app.dll.test.R;

public class TimetableFragment extends Fragment {
    public static String day = "";
    public static Bundle args = new Bundle();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Get the NavController
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        ImageButton back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> {

            // Navigate back to main_menu_button
            navController.navigate(R.id.action_timetable_fragment_to_main_menu_button);
        });

        Button sundayBtn = view.findViewById(R.id.sunday_btn);
        Button mondayBtn = view.findViewById(R.id.monday_btn);
        Button tuesdayBtn = view.findViewById(R.id.tuesday_btn);
        Button wednesdayBtn = view.findViewById(R.id.wednesday_btn);
        Button thursdayBtn = view.findViewById(R.id.thursday_btn);
        Button fridayBtn = view.findViewById(R.id.friday_btn);
        Button shabbatBtn = view.findViewById(R.id.shabbat_btn);

        //Navigate to daily times
        sundayBtn.setOnClickListener(v -> navigateToDailyZamanim("Sunday"));
        mondayBtn.setOnClickListener(v -> navigateToDailyZamanim("Monday"));
        tuesdayBtn.setOnClickListener(v -> navigateToDailyZamanim("Tuesday"));
        wednesdayBtn.setOnClickListener(v -> navigateToDailyZamanim("Wednesday"));
        thursdayBtn.setOnClickListener(v -> navigateToDailyZamanim("Thursday"));
        fridayBtn.setOnClickListener(v -> navigateToDailyZamanim("Friday"));
        shabbatBtn.setOnClickListener(v -> navigateToDailyZamanim("Shabbat"));




        // Set up the date format
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM", Locale.getDefault());

        // Get the calendar instance and adjust it to the current week's Monday
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Log.d("today", String.valueOf(today));
        // Loop through the days of the week and set button text
        for (int i = 0; i < 7; i++) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            String jewishDate = getJewishDayAndMonth(year, month, day);
            String buttonText = formatter.format(calendar.getTime()) + " " + jewishDate;

            switch (i) {
                case 0: // Sunday
                    if (today == Calendar.SUNDAY) {
                        sundayBtn.setText("Today\n" + buttonText);
                    } else {
                        sundayBtn.setText("Sunday\n" + buttonText);
                    }
                    break;
                case 1: // Monday
                    if (today == Calendar.MONDAY) {
                        mondayBtn.setText("Today\n" + buttonText);
                    } else {
                        mondayBtn.setText("Monday\n" + buttonText);
                    }
                    break;
                case 2: // Tuesday
                    if (today == Calendar.TUESDAY) {
                        tuesdayBtn.setText("Today\n" + buttonText);
                    } else {
                        tuesdayBtn.setText("Tuesday\n" + buttonText);
                    }
                    break;
                case 3: // Wednesday
                    if (today == Calendar.WEDNESDAY) {
                        wednesdayBtn.setText("Today\n" + buttonText);
                    } else {
                        wednesdayBtn.setText("Wednesday\n" + buttonText);
                    }
                    break;
                case 4: // Thursday
                    if (today == Calendar.THURSDAY) {
                        thursdayBtn.setText("Today\n" + buttonText);
                    } else {
                        thursdayBtn.setText("Thursday\n" + buttonText);
                    }
                    break;
                case 5: // Friday
                    if (today == Calendar.FRIDAY) {
                        fridayBtn.setText("Today\n" + buttonText);
                    } else {
                        fridayBtn.setText("Friday\n" + buttonText);
                    }
                    break;
                case 6: // Saturday (Shabbat)
                    if (today == Calendar.SATURDAY) {
                        shabbatBtn.setText("Today\n" + buttonText);
                    } else {
                        shabbatBtn.setText("Shabbat\n" + buttonText);
                    }
                    break;
            }

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    private void navigateToDailyZamanim(String dayOfWeek) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("dayOfWeek", dayOfWeek);
        navController.navigate(R.id.action_to_dalyZamanimFragment, bundle);
    }

}
