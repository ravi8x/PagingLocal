package info.androidhive.paging.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import info.androidhive.paging.db.dao.AnimalDao;
import info.androidhive.paging.db.entity.AnimalEntity;

@Database(entities = {AnimalEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AnimalDao animalDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "animals_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
