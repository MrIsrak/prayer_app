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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ImageButton back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
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

        // Set up the date format
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM", Locale.getDefault());

        // Get the calendar instance and adjust it to the current week's Monday
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // Loop through the days of the week and set button text
        for (int i = 0; i < 7; i++) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Adjust month to 1-based
            int day = calendar.get(Calendar.DAY_OF_MONTH);



            String jewishDate = getJewishDayAndMonth(year, month, day);
            String buttonText = formatter.format(calendar.getTime()) + " " + jewishDate;


            // Log the Gregorian date being processed
            Log.d("TimetableFragment", "Processing date: " + year + "-" + month + "-" + day + "\n " + jewishDate);

            switch (i) {
                case 0:
                    sundayBtn.setText("Sunday\n" + buttonText);
                    break;
                case 1:
                    mondayBtn.setText("Monday\n" + buttonText);
                    break;
                case 2:
                    tuesdayBtn.setText("Tuesday\n" + buttonText);
                    break;
                case 3:
                    wednesdayBtn.setText("Wednesday\n" + buttonText);
                    break;
                case 4:
                    thursdayBtn.setText("Thursday\n" + buttonText);
                    break;
                case 5:
                    fridayBtn.setText("Friday\n" + buttonText);
                    break;
                case 6:
                    shabbatBtn.setText("Shabbat\n" + buttonText);
                    break;
            }

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
