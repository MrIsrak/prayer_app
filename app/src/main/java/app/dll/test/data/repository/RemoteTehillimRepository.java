package app.dll.test.data.repository;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import app.dll.test.R;
import app.dll.test.data.apiService.ApiService;
import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.dto.SefriaTextResponse;
import app.dll.test.presentation.componets.TehillimViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteTehillimRepository {

    public final String TAG = "TEXT";

    public String bookName;
    public int chapterNumber;
    public String language;
    public ApiService apiService;

    public RemoteTehillimRepository(String bookName, int chapterNumber, String language, ApiService apiService) {
        this.bookName = bookName;
        this.chapterNumber = chapterNumber;
        this.language = language;
        this.apiService = apiService;
    }

    public void fetchSefriaText(View view, Context context, TextView tehilimTextView) {

        Log.d(TAG, "fetchSefariaText: Requesting text for book '" + bookName +
                "', chapter " + chapterNumber + ", language '" + language + "'");

        Call<SefriaTextResponse> getSefariaTextCall = apiService.getTehillimText(bookName, chapterNumber, language);

        getSefariaTextCall.enqueue(new Callback<SefriaTextResponse>() {
            @Override
            public void onResponse(Call<SefriaTextResponse> call, Response<SefriaTextResponse> response) {
                Log.d(TAG, "onResponse: Response Code: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    SefriaTextResponse sefariaResponse = response.body();

                    StringBuilder displayText = new StringBuilder();
                    if ("he".equals(language)) {
                        for (String verse : sefariaResponse.getHe()) {
                            displayText.append(verse).append("\n\n");
                        }
                    } else if ("en".equals(language)) {
                        for (String verse : sefariaResponse.getText()) {
                            displayText.append(verse).append("\n\n");
                        }
                    } else {
                        displayText.append(view.getResources().getString(R.string.textNotAvailableForLang));
                    }

                    if (displayText.length() > 2) {
                        displayText.setLength(displayText.length() - 2);
                    }

                    if (tehilimTextView != null) {
                        Spanned spanned = Html.fromHtml(cleanFootnotes(displayText.toString()), Html.FROM_HTML_MODE_LEGACY);
                        tehilimTextView.setText(spanned);
                    }

                    TehillimEntity entity = new TehillimEntity();
                    entity.bookName() = bookName;
                    entity.psalmText = cleanFootnotes(displayText.toString());

                    if (view.getContext() instanceof FragmentActivity) {
                        TehillimViewModel viewModel = new ViewModelProvider((FragmentActivity) view.getContext())
                                .get(TehillimViewModel.class);
                        viewModel.insert(entity);
                    }

                } else {
                    String error = context.getString(R.string.errorLoadingText) + ": " + response.code();
                    if (tehilimTextView != null) tehilimTextView.setText(error);
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SefriaTextResponse> call, Throwable t) {
                String error = context.getString(R.string.errorLoadingText) + ": " + t.getMessage();
                Log.e(TAG, "onFailure: ", t);
                if (tehilimTextView != null) tehilimTextView.setText(error);
                Toast.makeText(context, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String cleanFootnotes(String raw) {
        return raw
                .replaceAll("<sup[^>]*footnote-marker[^>]*>.*?</sup>", "")
                .replaceAll("<sup[^>]*endFootnote[^>]*>.*?</sup>", "")
                .replaceAll("<sup>.*?</sup>", "")
                .replaceAll("<i[^>]*footnote[^>]*>.*?</i>", "")
                .replaceAll("\\s*<br>\\s*", "<br>")
                .replaceAll(" +", " ")
                .trim();
    }
}
