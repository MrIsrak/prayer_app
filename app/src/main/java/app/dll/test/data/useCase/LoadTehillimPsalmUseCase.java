package app.dll.test.data.useCase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import javax.inject.Inject;

import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.repository.LocalTehillimRepository;
import app.dll.test.data.repository.RemoteTehillimRepository;
import app.dll.test.presentation.utils.Resource;

public class LoadTehillimPsalmUseCase {

    private final LocalTehillimRepository localTehillimRepository;
    private final RemoteTehillimRepository remoteTehillimRepository;

    @Inject
    public LoadTehillimPsalmUseCase(LocalTehillimRepository localTehillimRepository,
                                    RemoteTehillimRepository remoteTehillimRepository) {
        this.localTehillimRepository = localTehillimRepository;
        this.remoteTehillimRepository = remoteTehillimRepository;
    }

    /**
     * Loads a specific Tehillim psalm by chapter number using a "local-first" data strategy.
     * 1. Attempts to retrieve the psalm from the local database.
     * 2. If not found locally, it fetches from the remote API and saves it locally.
     * 3. It returns a LiveData that emits the status (LOADING, SUCCESS, ERROR) of the operation.
     *
     * @param bookName      The name of the book (e.g., "Psalms").
     * @param chapterNumber The chapter number of the psalm.
     * @param language      The language of the psalm (e.g., "he" for Hebrew, "en" for English).
     * @return LiveData<Resource<TehillimEntity>> A LiveData object indicating the status of the operation.
     */
    public LiveData<Resource<TehillimEntity>> loadTehillimPsalm(String bookName, int chapterNumber, String language) {
        MediatorLiveData<Resource<TehillimEntity>> result = new MediatorLiveData<>();

        // Add the local data source first
        LiveData<TehillimEntity> localSource = localTehillimRepository.getTehillimByChapter(chapterNumber);

        result.addSource(localSource, localEntity -> {
            // The first emission from the local source tells us if we have the data.
            result.removeSource(localSource);

            if (localEntity != null) {
                // Case 1: Data found locally. Immediately post SUCCESS.
                result.postValue(Resource.success(localEntity));
                Log.i("INFO", "loaded from local database");
            } else {
                // Case 2: Data not found. Proceed to fetch from remote.
                fetchFromRemoteAndSave(bookName, chapterNumber, language, result);
            }
        });

        return result;
    }

    private void fetchFromRemoteAndSave(String bookName, int chapterNumber, String language,
                                        MediatorLiveData<Resource<TehillimEntity>> result) {
        // Post LOADING status to inform the UI that an asynchronous operation is in progress.
        result.postValue(Resource.loading(null));

        // Add the remote data source
        LiveData<Resource<String>> remoteSource = remoteTehillimRepository.fetchSefariaText(bookName, chapterNumber, language);

        result.addSource(remoteSource, remoteResource -> {
            switch (remoteResource.status) {
                case SUCCESS:
                    String text = remoteResource.data;
                    TehillimEntity newEntity = new TehillimEntity(bookName, language, chapterNumber, text);

                    // Save the new entity to the local database.
                    localTehillimRepository.insertALL(newEntity);

                    // Post SUCCESS with the newly saved entity.
                    result.postValue(Resource.success(newEntity));

                    // The source can be removed now that the operation is complete.
                    result.removeSource(remoteSource);
                    break;
                case ERROR:
                    // Post the error.
                    result.postValue(Resource.error(remoteResource.message, null));

                    // The source can be removed now that the operation is complete.
                    result.removeSource(remoteSource);
                    break;
                // No LOADING case needed here as it was posted at the start.
            }
        });
    }
}