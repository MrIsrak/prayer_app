package app.dll.test.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import app.dll.test.data.apiService.ApiService;
import app.dll.test.data.dto.SefriaTextResponse;

import app.dll.test.presentation.utils.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for fetching Tehillim (Psalms) text from a remote API (Sefaria).
 * This class uses Retrofit for network requests and LiveData to expose the results
 * in a lifecycle-aware manner, encapsulating the loading, success, and error states
 * using the custom Resource class.
 */
public class RemoteTehillimRepository {

    private final ApiService apiService;

    /**
     * Constructor for RemoteTehillimRepository, injected by Dagger Hilt.
     * @param apiService The Retrofit ApiService instance for making network calls.
     */
    @Inject
    public RemoteTehillimRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Fetches Tehillim text from the Sefaria API.
     * This method returns a LiveData object that will be updated with the result
     * of the API call, including loading, success, or error states.
     *
     * @param bookName      The name of the book (e.g., "Psalms").
     * @param chapterNumber The chapter number.
     * @param language      The language of the text (e.g., "he" for Hebrew, "en" for English).
     * @return LiveData<Resource<String>> A LiveData object encapsulating the state and data of the API request.
     */
    public LiveData<Resource<String>> fetchSefariaText(String bookName, int chapterNumber, String language) {
        MutableLiveData<Resource<String>> resultLiveData = new MutableLiveData<>();

        resultLiveData.postValue(Resource.loading(null));

        Log.d("TEXT", "Requesting text for book '" + bookName + "', chapter " + chapterNumber + ", language '" + language + "'");

        Call<SefriaTextResponse> call = apiService.getTehillimText(bookName, chapterNumber, language);

        call.enqueue(new Callback<SefriaTextResponse>() {
            @Override
            public void onResponse(Call<SefriaTextResponse> call, Response<SefriaTextResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    StringBuilder displayText = StringBuilderFormat(response, language);

                    resultLiveData.postValue(Resource.success(displayText.toString()));
                } else {
                    String errorMessage = "Failed to load text. Code: " + response.code();
                    if (!response.message().isEmpty()) {
                        errorMessage += " - " + response.message();
                    }
                    resultLiveData.postValue(Resource.error(errorMessage, null));
                }
            }

            @Override
            public void onFailure(Call<SefriaTextResponse> call, Throwable t) {
                String errorMessage = "Network error: " + (t.getMessage() != null ? t.getMessage() : "Unknown error");
                resultLiveData.postValue(Resource.error(errorMessage, null));
            }
        });

        // Return the LiveData immediately. The actual value will be posted asynchronously.
        return resultLiveData;
    }

    @NonNull
    private StringBuilder StringBuilderFormat(Response<SefriaTextResponse> response, String language) {
        List<String> verses = "he".equalsIgnoreCase(language)
                ? response.body().getHe()
                : response.body().getText();


        StringBuilder displayText = new StringBuilder();
        if (verses != null) {
            for (String verse : verses) {
                displayText.append(verse).append("\n\n");
            }
        }


        if (displayText.length() > 2) {
            displayText.setLength(displayText.length() - 2);
        }
        return displayText;
    }
}
