package app.dll.test.presentation.componets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.dll.test.data.repository.LocalTehillimRepository;
import app.dll.test.data.entities.TehillimEntity;

public class TehillimViewModel extends AndroidViewModel {
    private final LocalTehillimRepository repository;
    public TehillimViewModel(@NonNull Application application) {
        super(application);
        repository = new LocalTehillimRepository(application);
    }

    public void insert(TehillimEntity entity) {
        repository.insertALL(entity);
    }


    public void deleteALL() {
        repository.deleteALL();
    }


    public LiveData<TehillimEntity> getTehillimById(int id) {
        return repository.getTehillimById(id);
    }


    public LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int startVerse, int endVerse) {
        return repository.getTehillimBetweenVerses(startVerse, endVerse);
    }

}
