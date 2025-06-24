package app.dll.test.data.cache;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import app.dll.test.data.entities.TehillimEntity;

@Dao
public interface TehillimDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TehillimEntity> texts);
    @Query("DELETE FROM tehillim_texts")
    void deleteALL();
    @Query("Select * FROM tehillim_texts WHERE chapter = :id ORDER BY chapter ASC")
    LiveData<TehillimEntity> getTehillimById(int id);
    @Query("SELECT * FROM tehillim_texts WHERE id BETWEEN :startingVerse AND :endingVerse ORDER BY id ASC")
    LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int startingVerse, int endingVerse);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TehillimEntity tehillim);
}
