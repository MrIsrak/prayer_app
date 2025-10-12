package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import static app.dll.test.presentation.utils.dateUtils.GetDate.getToday;
import static app.dll.test.presentation.utils.dateUtils.GetJewishDate.getDayOfJewishMonth;
import static app.dll.test.presentation.utils.dateUtils.SpecialDetails.dayListInnitialization;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.kosherjava.zmanim.hebrewcalendar.JewishDate;

import java.util.ArrayList;
import java.util.List;

import app.dll.test.R;
import app.dll.test.presentation.componets.TehillimViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DailyTehillimFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_tehillim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TehillimViewModel viewModel = new ViewModelProvider(this).get(TehillimViewModel.class);

        TextView dailyTehillimTV = view.findViewById(R.id.daily_tehillimTV);

        List<String> days = new ArrayList<>();
        dayListInnitialization(days, requireView());

        Spinner daySelectionSpinner = view.findViewById(R.id.daySelectionSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                days
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySelectionSpinner.setAdapter(adapter);
        // Set the current day as the selected item
        daySelectionSpinner.setSelection(getDayOfJewishMonth(new JewishDate()));

    }

}
