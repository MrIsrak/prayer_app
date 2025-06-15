package app.dll.test.fetchFunctions;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.text.HtmlCompat;

import app.dll.test.R;
import app.dll.test.apiService.ApiService;
import app.dll.test.pojo.SefariaTextResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class Fetch {
    public final String TAG = "TEXT";
    public String bookName;
    public int chapterNumber;
    public String language;
    public ApiService apiService;
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getTAG() {
        return TAG;
    }

    public String getBookName() {
        return bookName;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getLanguage() {
        return language;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public Fetch(String bookName, int chapterNumber, String language, ApiService apiService) {
        this.bookName = bookName;
        this.chapterNumber = chapterNumber;
        this.language = language;
        this.apiService = apiService;
    }

    public void fetchSefariaText(View view, Context context, TextView tehilimTextView) {

        Log.d(TAG, "fetchSefariaText: Requesting text for book '" + bookName +
                "', chapter " + chapterNumber + ", language '" + language + "'");

        Call<SefariaTextResponse> getSefariaTextCall = apiService.getSefariaText(bookName, chapterNumber, language);
        getSefariaTextCall.enqueue(new Callback<SefariaTextResponse>() {
            @Override
            public void onResponse(Call<SefariaTextResponse> call, Response<SefariaTextResponse> response) {
                Log.d(TAG, "onResponse: Request URL: " + call.request().url());
                Log.d(TAG, "onResponse: Response Code: " + response.code() + " " + response.message());

                if (response.isSuccessful() && response.body() != null) {
                    SefariaTextResponse sefariaResponse = response.body();
                    Log.d(TAG, "onResponse: API responded successfully. Book: " + sefariaResponse.getBook() +
                            ", Ref: " + sefariaResponse.getRef());

                    StringBuilder displayText = new StringBuilder();
                    if ("he".equals(language)) {
                        if (sefariaResponse.getHe() != null && !sefariaResponse.getHe().isEmpty()) {
                            Log.d(TAG, "onResponse: Processing Hebrew text. Number of verses: " + sefariaResponse.getHe().size());
                            for (String verse : sefariaResponse.getHe()) {
                                displayText.append(verse).append("\n\n");
                            }
                        } else {
                            Log.w(TAG, "onResponse: Hebrew text (sefariaResponse.getHe()) is empty or null.");
                            displayText.append(view.getResources().getString(R.string.textNotAvailable));
                        }
                    } else if ("en".equals(language)) {
                        if (sefariaResponse.getText() != null && !sefariaResponse.getText().isEmpty()) {
                            Log.d(TAG, "onResponse: Processing English text. Number of verses: " + sefariaResponse.getText().size());
                            for (String verse : sefariaResponse.getText()) {
                                displayText.append(verse).append("\n\n");
                            }
                        } else {
                            Log.w(TAG, "onResponse: English text (sefariaResponse.getText()) is empty or null.");
                            displayText.append(view.getResources().getString(R.string.textNotAvailable));
                        }
                    } else {
                        Log.i(TAG, "onResponse: Unsupported language: " + language);
                        displayText.append(view.getResources().getString(R.string.textNotAvailableForLang));
                    }

                    if (displayText.length() > 0) {
                        int lengthToRemove = Math.min(2, displayText.length());
                        displayText.setLength(displayText.length() - lengthToRemove);
                    }
                    Log.d(TAG, "onResponse: Final displayText length: " + displayText.length());

                    if (tehilimTextView != null) {
                        Log.d(TAG, "onResponse: Updating TextView. Text snippet: " +
                                (displayText.length() > 100 ? displayText.substring(0, 100) + "..." : displayText.toString()));
                        tehilimTextView.setText(HtmlCompat.fromHtml(displayText.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY));
                    } else {
                        Log.e(TAG, "onResponse: tehilimTextView is null. Text cannot be displayed.");
                    }

                    if (sefariaResponse.getVersions() != null && !sefariaResponse.getVersions().isEmpty()) {
                        Log.d(TAG, "onResponse: Number of versions: " + sefariaResponse.getVersions().size());
                    } else {
                        Log.d(TAG, "onResponse: Versions list is empty or null.");
                    }

                    if (sefariaResponse.getHeTitle() != null) {
                        Log.d(TAG, "onResponse: Hebrew title: " + sefariaResponse.getHeTitle());
                    } else {
                        Log.d(TAG, "onResponse: Hebrew title (HeTitle) is null.");
                    }

                } else {
                    Log.e(TAG, "onResponse: API returned an unsuccessful response. Code: " + response.code() + ", Message: " + response.message());
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                            Log.e(TAG, "onResponse: Error body: " + errorBody);
                        } else {
                            Log.w(TAG, "onResponse: response.errorBody() is null.");
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: Error reading errorBody: " + e.getMessage(), e);
                    }
                    if (tehilimTextView != null) {
                        tehilimTextView.setText(view.getResources().getString(R.string.errorLoadingText) + ": " + response.code());
                    }
                    Toast.makeText(context, view.getResources().getString(R.string.errorLoadingText) + ": " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SefariaTextResponse> call, Throwable t) {

            }
        });
    }
}
