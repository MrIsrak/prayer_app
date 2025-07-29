package app.dll.test.presentation.componets;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.MediatorLiveData;


import java.util.List;

import javax.inject.Inject;

import app.dll.test.data.repository.LocalTehillimRepository;
import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.repository.RemoteTehillimRepository;

import app.dll.test.presentation.utils.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TehillimViewModel extends AndroidViewModel {

    private final LocalTehillimRepository localRepository;
    private final RemoteTehillimRepository remoteRepository;

    private final MutableLiveData<Resource<TehillimEntity>> _apiTextLoadStatus = new MutableLiveData<>();
    public LiveData<Resource<TehillimEntity>> getApiTextLoadStatus() {
        return _apiTextLoadStatus;
    }

    private MediatorLiveData<Resource<String>> _repoTextLoadSource = new MediatorLiveData<>();


    @Inject
    public TehillimViewModel(@NonNull Application application,
                             LocalTehillimRepository localRepo,
                             RemoteTehillimRepository remoteRepo) {
        super(application);
        this.localRepository = localRepo;
        this.remoteRepository = remoteRepo;
    }

    /**
     * Retrieves a LiveData object containing a list of TehillimEntity objects
     * between specified start and end verses from the local repository.
     * @param start The starting verse number.
     * @param end The ending verse number.
     * @return LiveData<List<TehillimEntity>> A LiveData object that will be updated with the list of TehillimEntity.
     */
    public LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int start, int end) {
        return localRepository.getTehillimBetweenVerses(start, end);
    }

    /**
     * Inserts a TehillimEntity into the local repository.
     * @param entity The TehillimEntity to be inserted.
     */
    public void insertLocally(TehillimEntity entity) {
        localRepository.insertALL(entity);
    }

    /**
     * Loads text from a remote API (Sefaria) and saves it locally.
     * The loaded text is converted into a TehillimEntity before local insertion.
     * The loading status (loading, success, error) is communicated via LiveData.
     * @param book The name of the book (e.g., "Psalms").
     * @param chapter The chapter number.
     * @param lang The language of the text (e.g., "he" for Hebrew, "en" for English).
     */
    public void loadFromApiAndSave(String book, int chapter, String lang) {
        _apiTextLoadStatus.postValue(Resource.loading(null));

        LiveData<Resource<String>> repoSource = remoteRepository.fetchSefariaText(book, chapter, lang);

        _repoTextLoadSource.addSource(repoSource, stringResource -> {
            switch (stringResource.status) {
                case SUCCESS:
                    String text = stringResource.data;

                    TehillimEntity entity = new TehillimEntity(book, lang, chapter, text);

                    localRepository.insertALL(entity);
                    _apiTextLoadStatus.postValue(Resource.success(entity));
                    _repoTextLoadSource.removeSource(repoSource);
                    break;
                case ERROR:
                    System.err.println("Error loading Tehillim text from API: " + stringResource.message);
                    _apiTextLoadStatus.postValue(Resource.error(stringResource.message, null));
                    _repoTextLoadSource.removeSource(repoSource);
                    break;

            }
        });
    }
}
