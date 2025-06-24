package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import static android.app.ProgressDialog.show;

import static app.dll.test.presentation.utils.dateUtils.SpecialDetails.hebrewNumbersToGematria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import app.dll.test.R;
import app.dll.test.data.apiService.ApiService;
import app.dll.test.data.apiService.RetrofitClient;
import app.dll.test.data.repository.RemoteTehillimRepository;

public class DailyTehilimFragment extends Fragment {

    private String TAG = "TEXT";
    TextView psalmTextView;
    TextView tehilimTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_tehilim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        ApiService apiService = RetrofitClient.getApiService(requireContext());

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences langState = requireContext().getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
                String psalm = parent.getItemAtPosition(position).toString();
                int numChapter = 0;
                RemoteTehillimRepository remoteTehillimRepository = new RemoteTehillimRepository(psalm, numChapter, langState.getString("langPrefs", "en"), apiService);

                if (langState.getString("langPrefs", "en").equals("en")) {
                    numChapter = Integer.parseInt(psalm.substring(psalm.indexOf(" ") + 1));
                    Log.d(TAG, "onItemSelected: " + langState.getString("langPrefs", "en"));
                    remoteTehillimRepository.setChapterNumber(numChapter);
                    remoteTehillimRepository.fetchSefriaText(requireView(), requireContext(), tehilimTextView);
                } else if (langState.getString("langPrefs", "en").equals("he")) {
                    numChapter = hebrewNumbersToGematria(psalm.substring(psalm.indexOf(" ") + 1));
                    remoteTehillimRepository.setChapterNumber(numChapter);
                    remoteTehillimRepository.fetchSefriaText(requireView(), requireContext(), tehilimTextView);
                } else {
                    Log.d(TAG, "else onItemSelected: " + langState.getString("langPrefs", "en"));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}


