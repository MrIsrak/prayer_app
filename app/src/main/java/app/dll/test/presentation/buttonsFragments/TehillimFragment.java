package app.dll.test.presentation.buttonsFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import app.dll.test.R;

public class TehillimFragment extends Fragment implements View.OnClickListener {

    MaterialButton dailyTehillimBtn, chapterBtn;
    private static final String TAG = "Israel";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tehillim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "INITIALIZE");
        dailyTehillimBtn = view.findViewById(R.id.daily_Tehilim_btn);
        chapterBtn = view.findViewById(R.id.chapter_btn);
        Log.d(TAG, String.valueOf(view.findViewById(R.id.chapter_btn)));
        chapterBtn.setOnClickListener(this);
        dailyTehillimBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick triggered");
        NavController navController = Navigation.findNavController(view);
        if(view.getId() == R.id.daily_Tehilim_btn){
            Log.d(TAG, "onClick: clicked daily");
            navController.navigate(R.id.action_tehillim_fragment_to_daily_tehillim_fragment);
            }
        else if(view.getId() == R.id.chapter_btn){
            Log.d(TAG, "onClick: clicked chapter");
            navController.navigate(R.id.action_tehillim_fragment_to_allTehillimFragment);
        }
    }
}