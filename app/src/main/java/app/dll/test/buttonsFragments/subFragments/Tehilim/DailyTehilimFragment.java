package app.dll.test.buttonsFragments.subFragments.Tehilim;

import static android.app.ProgressDialog.show;

import static app.dll.test.zmanim.SpecialDetails.hebrewNumbersToGematria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import app.dll.test.R;
import app.dll.test.apiService.ApiService;
import app.dll.test.apiService.RetrofitClient;
import app.dll.test.fetchFunctions.Fetch;
import app.dll.test.pojo.SefariaTextResponse;
import app.dll.test.zmanim.TehillimDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiService apiService = RetrofitClient.getApiService();

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences langState = requireContext().getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
                String psalm = parent.getItemAtPosition(position).toString();
                int numChapter = 0;
                Fetch fetch = new Fetch(psalm, numChapter, langState.getString("langPrefs", "en"), apiService);

                if (langState.getString("langPrefs", "en").equals("en")) {
                    numChapter = Integer.parseInt(psalm.substring(psalm.indexOf(" ") + 1));
                    Log.d(TAG, "onItemSelected: " + langState.getString("langPrefs", "en"));
                    fetch.setChapterNumber(numChapter);
                    fetch.fetchSefariaText(requireView(), requireContext(), tehilimTextView);
                } else if (langState.getString("langPrefs", "en").equals("he")) {
                    numChapter = hebrewNumbersToGematria(psalm.substring(psalm.indexOf(" ") + 1));
                    fetch.setChapterNumber(numChapter);
                    fetch.fetchSefariaText(requireView(), requireContext(), tehilimTextView);
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


