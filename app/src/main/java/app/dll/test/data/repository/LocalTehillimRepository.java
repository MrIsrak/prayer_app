package app.dll.test.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import app.dll.test.data.dao.TehillimDao;
import app.dll.test.data.database.TehillimDatabase;
import app.dll.test.data.entities.TehillimEntity;

public class LocalTehillimRepository {
    private final TehillimDao tehillimDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public LocalTehillimRepository(Application application) {
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



