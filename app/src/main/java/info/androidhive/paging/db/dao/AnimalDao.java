package info.androidhive.paging.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import info.androidhive.paging.db.entity.AnimalEntity;

@Dao
public interface AnimalDao {
    @Query("SELECT * FROM animals ORDER BY name ASC")
    LiveData<List<AnimalEntity>> getAllAnimals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnimals(List<AnimalEntity> animals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(AnimalEntity animal);

    @Query("DELETE FROM animals")
    void deleteAll();
}
