package app.dll.test.buttonsFragments;

import static app.dll.test.dateTime.GetDate.getDayString;
import static app.dll.test.dateTime.GetDate.getToday;
import static app.dll.test.dateTime.GetDate.getTodayString;
import static app.dll.test.dateTime.GetDate.today;
import static app.dll.test.zmanim.GetJewishDate.getJewishDayAndMonth;

import android.graphics.PorterDuff;

import app.dll.test.dateTime.GetDate;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import app.dll.test.R;
import app.dll.test.userDataPrefs.userLocationData.GetLocation;

public class TimetableFragment extends Fragment {
    public static Map<Integer, String> dates = new HashMap<>();
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
        GetLocation.getLastLocation(requireActivity());
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
        Button shabbatBtn = view.findViewById(R.id.shabat_btn);

        //Navigate to daily times
        sundayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.sunday), view));
        mondayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.monday), view));
        tuesdayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.tuesday), view));
        wednesdayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.wednesday), view));
        thursdayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.thursday), view));
        fridayBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.friday), view));
        shabbatBtn.setOnClickListener(v -> navigateToDailyZamanim(view.getResources().getString(R.string.shabat), view));

        // Set up the date format
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM", Locale.getDefault());

        // Get the calendar instance and adjust it to the current week's Monday
        getToday();
        Calendar calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // Loop through the days of the week and set button text
        for (int i = 0; i < 7; i++) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            String jewishDate = getJewishDayAndMonth(year, month, day, view);
            String buttonText = formatter.format(calendar.getTime()) + " " + jewishDate;
            String todayString = getTodayString(view) + "\n" + buttonText;

            switch (i) {
                case 0: // Sunday
                    if (today == Calendar.SUNDAY) {
                        sundayBtn.setText(todayString);
                    } else {
                        sundayBtn.setText(view.getResources().getString(R.string.sunday) + "\n" + buttonText);
                    }
                    break;
                case 1: // Monday
                    if (today == Calendar.MONDAY) {
                        mondayBtn.setText(todayString);
                    } else {
                        mondayBtn.setText(view.getResources().getString(R.string.monday) + "\n" + buttonText);
                    }
                    break;
                case 2: // Tuesday
                    if (today == Calendar.TUESDAY) {
                        tuesdayBtn.setText(todayString);
                    } else {
                        tuesdayBtn.setText(view.getResources().getString(R.string.tuesday) + "\n" + buttonText);
                    }
                    break;
                case 3: // Wednesday
                    if (today == Calendar.WEDNESDAY) {
                        wednesdayBtn.setText(todayString);
                    } else {
                        wednesdayBtn.setText(view.getResources().getString(R.string.wednesday) + "\n" + buttonText);
                    }
                    break;
                case 4: // Thursday
                    if (today == Calendar.THURSDAY) {
                        thursdayBtn.setText(todayString);
                    } else {
                        thursdayBtn.setText(view.getResources().getString(R.string.thursday) + "\n" + buttonText);
                    }
                    break;
                case 5: // Friday
                    if (today == Calendar.FRIDAY) {
                        fridayBtn.setText(todayString);
                    } else {
                        fridayBtn.setText(view.getResources().getString(R.string.friday) + "\n" + buttonText);
                    }
                    break;
                case 6: // Saturday (Shabbat)
                    if (today == Calendar.SATURDAY) {
                        shabbatBtn.setText(todayString);
                    } else {
                        shabbatBtn.setText(view.getResources().getString(R.string.shabat) + "\n" + buttonText);
                    }
                    break;
            }

            //fill the HashMap with dates
            dates.put(i, buttonText);

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    private void navigateToDailyZamanim(String dayOfWeek, View view) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        getToday();
        Bundle dayDate = new Bundle();
        if (dayOfWeek.equals(getDayString(today, requireContext()))) {
            dayDate.putString("dayOfWeek", getTodayString(view)); // Use "Today" for the current day.
        } else {
            dayDate.putString("dayOfWeek", dayOfWeek); // Use the provided day of the week.
        }

        navController.navigate(R.id.action_to_dalyZamanimFragment, dayDate);
    }

}
