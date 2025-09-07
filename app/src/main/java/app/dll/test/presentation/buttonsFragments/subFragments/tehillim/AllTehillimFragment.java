package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import static app.dll.test.presentation.utils.dateUtils.SpecialDetails.hebrewNumbersToGematria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.dll.test.R;
import app.dll.test.presentation.componets.TehillimViewModel;

public class AllTehillimFragment extends Fragment {


    TextView psalmTextView;
    TextView tehilimTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_tehillim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TehillimViewModel tehillimViewModel = new ViewModelProvider(this).get(TehillimViewModel.class);

        psalmTextView = view.findViewById(R.id.psalmSelectTV);
        tehilimTextView = view.findViewById(R.id.tehillitmTextView);
        Spinner selectionSpinner = view.findViewById(R.id.psalmSelectionSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.psalms_list,
                android.R.layout.preference_category
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectionSpinner.setAdapter(adapter);

        // This single observer listens for all state changes from the ViewModel.
        tehillimViewModel.getPsalmLoadStatus().observe(getViewLifecycleOwner(), resource -> {
            if (resource != null) {
                switch (resource.status) {
                    case LOADING:
                        tehilimTextView.setText(requireContext().getString(R.string.loading));
                        selectionSpinner.setEnabled(false);
                        break;
                    case SUCCESS:
                        selectionSpinner.setEnabled(true);
                        if (resource.data != null && resource.data.getContent() != null) {
                            tehilimTextView.setText(resource.data.getContent());
                            Toast.makeText(getContext(), requireContext().getString(R.string.psalm_loaded), Toast.LENGTH_SHORT).show();
                        } else {
                            tehilimTextView.setText("No content found for this psalm.");
                        }
                        break;
                    case ERROR:
                        tehilimTextView.setText(requireContext().getString(R.string.errorLoadingText));
                        selectionSpinner.setEnabled(true);
                        Toast.makeText(getContext(), requireContext().getString(R.string.psalm_error), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences langState = requireContext().getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
                String psalmStr = (String) parent.getItemAtPosition(position);
                int psalm;

                if (langState.getString("langPrefs", "en").equals("he")) {
                    // Extract the number from the Hebrew string using Gematria
                    psalm = hebrewNumbersToGematria(psalmStr.substring(psalmStr.indexOf(" ") + 1));
                } else {
                    // Extract the number from the English string
                    psalm = Integer.parseInt(psalmStr.replaceAll("\\D+", ""));
                }

                // Call the correct method on the ViewModel, which will trigger the Use Case
                tehillimViewModel.loadPsalm("Psalms", psalm, langState.getString("langPrefs", "en"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }
}