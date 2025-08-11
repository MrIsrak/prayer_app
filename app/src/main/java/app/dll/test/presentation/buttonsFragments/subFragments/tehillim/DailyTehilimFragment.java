package app.dll.test.presentation.buttonsFragments.subFragments.tehillim;

import static android.app.ProgressDialog.show;

import static app.dll.test.presentation.utils.dateUtils.SpecialDetails.hebrewNumbersToGematria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import app.dll.test.data.apiService.ApiService;
import app.dll.test.data.apiService.RetrofitClient;
import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.repository.RemoteTehillimRepository;
import app.dll.test.presentation.componets.TehillimViewModel;
import app.dll.test.presentation.utils.Resource;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DailyTehilimFragment extends Fragment {
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

        tehillimViewModel.getApiTextLoadStatus().observe(getViewLifecycleOwner(), new Observer<Resource<TehillimEntity>>() {
            @Override
            public void onChanged(Resource<TehillimEntity> tehillimEntityResource) {
                switch (tehillimEntityResource.status) {
                    case LOADING:
                        tehilimTextView.setText(requireContext().getString(R.string.loading));
                        selectionSpinner.setEnabled(false);
                        break;
                    case SUCCESS:
                        selectionSpinner.setEnabled(true);
                        tehilimTextView.setText(tehillimEntityResource.data.getContent());
                        Toast.makeText(getContext(), requireContext().getString(R.string.psalm_loaded), Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        tehilimTextView.setText(requireContext().getString(R.string.errorLoadingText));
                        selectionSpinner.setEnabled(true);
                        Toast.makeText(getContext(), requireContext().getString(R.string.psalm_error), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //ApiService apiService = RetrofitClient.getApiService(requireContext());

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences langState = requireContext().getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
                int psalm = (int) parent.getItemAtPosition(position);
//                int numChapter = 0;
//                RemoteTehillimRepository remoteTehillimRepository = new RemoteTehillimRepository(psalm, numChapter, langState.getString("langPrefs", "en"), apiService);
//
//                if (langState.getString("langPrefs", "en").equals("en")) {
//                    numChapter = Integer.parseInt(psalm.substring(psalm.indexOf(" ") + 1));
//                    Log.d(TAG, "onItemSelected: " + langState.getString("langPrefs", "en"));
//                    //remoteTehillimRepository.setChapterNumber(numChapter);
//                    remoteTehillimRepository.fetchSefriaText(requireView(), requireContext());
//                } else if (langState.getString("langPrefs", "en").equals("he")) {
//                    numChapter = hebrewNumbersToGematria(psalm.substring(psalm.indexOf(" ") + 1));
//                    //remoteTehillimRepository.setChapterNumber(numChapter);
//                    remoteTehillimRepository.fetchSefriaText(requireView(), requireContext());
//                } else {
//                    Log.d(TAG, "else onItemSelected: " + langState.getString("langPrefs", "en"));
//                }
                tehillimViewModel.loadFromApiAndSave("Psalms", psalm, langState.getString("langPrefs", "en"));


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}


