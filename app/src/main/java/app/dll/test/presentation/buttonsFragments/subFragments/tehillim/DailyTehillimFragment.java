package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    }

}
