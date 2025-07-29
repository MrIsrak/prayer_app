package app.dll.test.data.dao;

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

    @Query("SELECT * FROM tehillim_texts WHERE chapter BETWEEN :startingVerse AND :endingVerse ORDER BY chapter ASC")
    LiveData<List<TehillimEntity>> getTehillimBetweenVerses(int startingVerse, int endingVerse);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TehillimEntity tehillim);
    @Query("SELECT EXISTS(SELECT 1 FROM tehillim_texts WHERE chapter = :number)")
    boolean existingByChapter(int number);
    @Query("SELECT * FROM tehillim_texts WHERE chapter = :chapter")
    LiveData<TehillimEntity> getTehillimByChapter(int chapter);
}
