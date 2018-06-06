package info.androidhive.paging.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import info.androidhive.paging.db.dao.AnimalDao;
import info.androidhive.paging.db.entity.AnimalEntity;

public class AnimalRepository {
    private AnimalDao mAnimalDao;
    private LiveData<List<AnimalEntity>> mAllAnimals;

    public AnimalRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAnimalDao = db.animalDao();
        mAllAnimals = mAnimalDao.getAllAnimals();
    }

    public LiveData<List<AnimalEntity>> getAllAnimals() {
        return mAllAnimals;
    }

    public void insertAnimal(AnimalEntity animal) {
        new insertAnimalAsync(mAnimalDao).execute(animal);
    }

    public void insertAnimals(List<AnimalEntity> animals) {
        new insertAnimalsAsync(mAnimalDao).execute(animals);
    }

    private static class insertAnimalAsync extends AsyncTask<AnimalEntity, Void, Long> {

        private AnimalDao mAnimalDaoAsync;

        insertAnimalAsync(AnimalDao noteDao) {
            mAnimalDaoAsync = noteDao;
        }

        @Override
        protected Long doInBackground(AnimalEntity... animals) {
            long id = mAnimalDaoAsync.insert(animals[0]);
            return id;
        }
    }

    private static class insertAnimalsAsync extends AsyncTask<List<AnimalEntity>, Void, Void> {

        private AnimalDao mAnimalDaoAsync;

        insertAnimalsAsync(AnimalDao noteDao) {
            mAnimalDaoAsync = noteDao;
        }

        @Override
        protected Void doInBackground(List<AnimalEntity>... animals) {
            mAnimalDaoAsync.insertAnimals(animals[0]);
            return null;
        }
    }
}
