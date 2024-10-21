package app.dll.test.buttonsFragments;

import android.graphics.PorterDuff;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
    }
}
