package app.dll.test.buttonsFragments.subFragments.Tehilim;

import static android.app.ProgressDialog.show;

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
import app.dll.test.pojo.SefariaTextResponse;
import app.dll.test.zmanim.TehillimDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyTehilimFragment extends Fragment {
    private ApiService apiService;
    private String TAG = "TEXT";
    TextView psalmTextView;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        psalmTextView = view.findViewById(R.id.psalmSelectTV);
        PDFView pdfView = view.findViewById(R.id.pdfView);
        pdfView.fromAsset("Tehillim.pdf")
                .pages(TehillimDetails.getDailyTehillim())
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .pageFitPolicy(FitPolicy.WIDTH)
                .spacing(10)
                .load();
        Spinner selectionSpinner = view.findViewById(R.id.psalmSelectionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.psalms_list,
                android.R.layout.preference_category
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectionSpinner.setAdapter(adapter);

        apiService = RetrofitClient.getApiService();
        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences langState = requireContext().getSharedPreferences("langPrefs", Context.MODE_PRIVATE);
                String psalm = parent.getItemAtPosition(position).toString();
                int numChapter = Integer.parseInt(psalm.substring(psalm.indexOf(" ") + 1));
                if(langState.getString("langPrefs", "en").equals("English")){
                    fetchSefariaText(psalm, numChapter, "en");
                }
                else if(langState.getString("langPrefs", "en").equals("Hebrew")){
                    fetchSefariaText(psalm, numChapter, "he");
                }
                else{}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }

    private void fetchSefariaText(String bookName, int chapterNumber, String language) {

        Call<SefariaTextResponse> getSefariaTextCall = apiService.getSefariaText(bookName, chapterNumber, language);
        getSefariaTextCall.enqueue(new Callback<SefariaTextResponse>() {
            @Override
            public void onResponse(Call<SefariaTextResponse> call, Response<SefariaTextResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    SefariaTextResponse sefariaResponse = response.body();


                    StringBuilder displayText = new StringBuilder();
                    if ("he".equals(language)) {
                        for (String verse : sefariaResponse.getHe()) {
                            displayText.append(verse).append("\n\n");
                        }
                    }
                    else if ("en".equals(language)){
                        for (String verse : sefariaResponse.getText()) {
                            displayText.append(verse).append("\n\n");
                        }
                    } else{

                    }
                    if (displayText.length() > 0) {
                        displayText.setLength(displayText.length() - 2);
                    }
                    if (psalmTextView != null) {
                        psalmTextView.setText(HtmlCompat.fromHtml(displayText.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY));
                    }

                } else {
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<SefariaTextResponse> call, Throwable t) {
                Log.e(TAG, getResources().getString(R.string.networkError)+ " " + t.getMessage(), t);
                Toast.makeText(requireActivity(), getResources().getString(R.string.networkError) + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}