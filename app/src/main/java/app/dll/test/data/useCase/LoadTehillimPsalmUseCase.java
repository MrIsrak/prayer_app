package app.dll.test.data.useCase;

import static dagger.hilt.android.internal.Contexts.getApplication;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import app.dll.test.data.dao.TehillimDao;
import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.repository.LocalTehillimRepository;
import app.dll.test.data.repository.RemoteTehillimRepository;
import app.dll.test.presentation.componets.TehillimViewModel;
import app.dll.test.presentation.utils.Resource;
import app.dll.test.presentation.utils.specialUtils.NetworkUtils;

public class LoadTehillimPsalmUseCase {
    private final LocalTehillimRepository localTehillimRepository;
    private final RemoteTehillimRepository remoteTehillimRepository;
    private final TehillimDao tehillimDao;

    @Inject
    public LoadTehillimPsalmUseCase(LocalTehillimRepository localTehillimRepository,
                                    RemoteTehillimRepository remoteTehillimRepository,
                                    TehillimDao tehillimDao) {
        this.localTehillimRepository = localTehillimRepository;
        this.remoteTehillimRepository = remoteTehillimRepository;
        this.tehillimDao = tehillimDao;
    }

    /**
     * Loads a specific Tehillim psalm by chapter number.
     * It implements a "local-first" data strategy:
     * 1. It first attempts to retrieve the psalm from the local database.
     * 2. If the psalm is found locally, it immediately returns the local copy.
     * 3. If the psalm is not found locally, it fetches it from the remote API.
     * 4. Upon successful remote fetch, the text is saved to the local database.
     * 5. Finally, the newly saved local copy (or the original local copy) is returned.
     *
     * This method returns a LiveData that emits the loading status and the TehillimEntity.
     * The ViewModel (or Presentation layer) should observe this LiveData to react to changes
     * in data state (loading, success, error) and update the UI accordingly.
     *
     * @param bookName      The name of the book (e.g., "Psalms").
     * @param chapterNumber The chapter number of the psalm.
     * @param language      The language of the psalm (e.g., "he" for Hebrew, "en" for English).
     * @return LiveData<Resource<TehillimEntity>> A LiveData object indicating the status (LOADING, SUCCESS, ERROR)
     * and the TehillimEntity if the operation is successful.
     */
    public LiveData<Resource<TehillimEntity>> loadTehillimPsalm(String bookName, int chapterNumber, String language) {
        MediatorLiveData<Resource<TehillimEntity>> result = new MediatorLiveData<>();
        result.postValue(Resource.loading(null));

        LiveData<TehillimEntity> localSource = localTehillimRepository.getTehillimByChapter(chapterNumber);

        result.addSource(localSource, new Observer<TehillimEntity>() {
            @Override
            public void onChanged(TehillimEntity localEntity) {
                result.removeSource(localSource);

                if (localEntity != null) {
                    result.postValue(Resource.success(localEntity));
                } else {
                    fetchFromRemoteAndSave(bookName, chapterNumber, language, result);
                }
            }
        });

        return result;
    }

    private void fetchFromRemoteAndSave(String bookName, int chapterNumber, String language,
                                        MediatorLiveData<Resource<TehillimEntity>> result) {

        LiveData<Resource<String>> remoteSource = remoteTehillimRepository.fetchSefariaText(bookName, chapterNumber, language);

        result.addSource(remoteSource, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> remoteResource) {
                switch (remoteResource.status) {
                    case SUCCESS:
                        String text = remoteResource.data;
                        TehillimEntity newEntity = new TehillimEntity( bookName, language, chapterNumber, text);

                        localTehillimRepository.insertALL(newEntity);

                        result.postValue(Resource.success(newEntity));
                        result.removeSource(remoteSource);
                        break;
                    case ERROR:
                        result.postValue(Resource.error(remoteResource.message, null));
                        result.removeSource(remoteSource);
                        break;
                    case LOADING:
                        break;
                }
            }
        });
    }
}
