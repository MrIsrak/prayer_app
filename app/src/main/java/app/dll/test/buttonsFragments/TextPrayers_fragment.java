package app.dll.test.buttonsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<<< HEAD:app/src/main/java/app/dll/test/buttonsFragments/TextPrayers_fragment.java

import app.dll.test.R;

public class TextPrayers_fragment extends Fragment {
========
 
public class TextPrayersFragment extends Fragment {
>>>>>>>> origin/master:app/src/main/java/app/dll/test/TextPrayersFragment.java

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_prayers, container, false);
    }
}