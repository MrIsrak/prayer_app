package app.dll.test.data.cache;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import app.dll.test.data.entities.TehillimEntity;

@Database(entities = {TehillimEntity.class}, version = 1, exportSchema = false)
public abstract class TehillimDatabase extends RoomDatabase {

    private static volatile TehillimDatabase INSTANCE;

    public abstract TehillimDao tehillimDao();

    public static TehillimDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TehillimDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TehillimDatabase.class,
                            "tehillim_database"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
