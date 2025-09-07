package app.dll.test.presentation.componets;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;
import javax.inject.Inject;

import app.dll.test.data.entities.TehillimEntity;
import app.dll.test.data.repository.LocalTehillimRepository;
import app.dll.test.data.useCase.LoadTehillimPsalmUseCase;
import app.dll.test.presentation.utils.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TehillimViewModel extends AndroidViewModel {

    // Dependencies injected via Hilt
    private final LocalTehillimRepository localRepository;
    private final LoadTehillimPsalmUseCase loadTehillimUseCase;

    // LiveData to hold the status and data for a single psalm fetch.
    // This is observed by the UI.
    private final MediatorLiveData<Resource<TehillimEntity>> _psalmLoadStatus = new MediatorLiveData<>();
    public LiveData<Resource<TehillimEntity>> getPsalmLoadStatus() {
        return _psalmLoadStatus;
    }

    @Inject
    public TehillimViewModel(@NonNull Application application,
                             LocalTehillimRepository localRepo,
                             LoadTehillimPsalmUseCase loadTehillimUseCase) {
        super(application);
        this.localRepository = localRepo;
        this.loadTehillimUseCase = loadTehillimUseCase;
    }

    /**
     * Retrieves a LiveData object containing a list of TehillimEntity objects
     * between specified start and end verses from the local repository.
     * This method is a direct pass-through to the repository.
     * @param start The starting verse number.
     * @param end The ending verse number.
     * @return LiveData<List<TehillimEntity>> A LiveData object that will be updated with the list of TehillimEntity.
     */
    public LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int start, int end) {
        return localRepository.getTehillimBetweenVerses(start, end);
    }

    /**
     * Inserts a TehillimEntity into the local repository.
     * This is a simple pass-through method.
     * @param entity The TehillimEntity to be inserted.
     */
    public void insertLocally(TehillimEntity entity) {
        localRepository.insertALL(entity);
    }

    /**
     * Initiates the process of loading a psalm. The ViewModel delegates the business logic
     * to the `LoadTehillimPsalmUseCase`. The UI should observe `getPsalmLoadStatus()`
     * to get updates on the data state.
     * @param book The name of the book (e.g., "Psalms").
     * @param chapter The chapter number.
     * @param lang The language of the text (e.g., "he" for Hebrew, "en" for English).
     */
    public void loadPsalm(String book, int chapter, String lang) {
        // Use the Use Case to perform the data fetching and saving logic.
        // The Use Case will handle the local-first strategy and network fetching.
        LiveData<Resource<TehillimEntity>> useCaseSource = loadTehillimUseCase.loadTehillimPsalm(book, chapter, lang);

        // Add the Use Case's LiveData as a source to the ViewModel's LiveData.
        // This ensures that any updates from the Use Case are passed to the UI.
        _psalmLoadStatus.addSource(useCaseSource, resource -> {
            // Post the value to the ViewModel's LiveData
            _psalmLoadStatus.setValue(resource);

            // Clean up the source when the operation is complete (SUCCESS or ERROR)
            if (resource.status == Resource.Status.SUCCESS || resource.status == Resource.Status.ERROR) {
                _psalmLoadStatus.removeSource(useCaseSource);
            }
        });
    }
}