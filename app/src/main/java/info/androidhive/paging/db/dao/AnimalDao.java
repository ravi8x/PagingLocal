package info.androidhive.paging.db.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import info.androidhive.paging.db.entity.AnimalEntity;

@Dao
public interface AnimalDao {
    @Query("SELECT * FROM animals ORDER BY name ASC")
    DataSource.Factory<Integer, AnimalEntity> getAllAnimalsPaged();

    @Query("SELECT * FROM animals WHERE id=:id")
    AnimalEntity getAnimal(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnimals(List<AnimalEntity> animals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(AnimalEntity animal);

    @Update
    void update(AnimalEntity animal);

    @Query("DELETE FROM animals")
    void deleteAll();
}
