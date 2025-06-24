package app.dll.test.presentation.buttonsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import app.dll.test.R;

public class TehilimFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tehillim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button dailyTehilimBtn = view.findViewById(R.id.daily_Tehilim_btn);
        Button chapterBtn = view.findViewById(R.id.chapter_btn);
        Button situationBtn = view.findViewById(R.id.situation_btn);
        Button booksBtn = view.findViewById(R.id.books);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        dailyTehilimBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_tehilim_fragment_to_daily_tehilim_fragment);
        });



    }
}