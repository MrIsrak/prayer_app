package app.dll.test.data.cache;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import app.dll.test.data.entities.TehillimEntity;

public class TehillimRepository {
    private final TehillimDao tehillimDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public TehillimRepository(Application application) {
        TehillimDatabase db = TehillimDatabase.getInstance(application);
        tehillimDao = db.tehillimDao();
    }

    public void insertALL(TehillimEntity entity){
        executor.execute(() -> tehillimDao.insert(entity));}
    public void deleteALL(){executor.execute(() -> tehillimDao.deleteALL());}
    public LiveData<TehillimEntity> getTehillimById(int id) {
        return tehillimDao.getTehillimById(id);
    }
    public LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int startingVerse, int endingVerse){
        return tehillimDao.getTehillimBetweenVerses(startingVerse, endingVerse);}
}



