package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import static app.dll.test.presentation.utils.dateUtils.SpecialDetails.hebrewNumbersToGematria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.dll.test.R;
import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.presentation.componets.TehillimViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import app.dll.test.presentation.utils.Resource.Status;

@AndroidEntryPoint
public class DailyTehilimFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_tehilim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
