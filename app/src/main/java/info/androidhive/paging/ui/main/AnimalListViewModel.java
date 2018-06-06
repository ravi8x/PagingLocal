package info.androidhive.paging.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import info.androidhive.paging.db.AnimalRepository;
import info.androidhive.paging.db.entity.AnimalEntity;

public class AnimalListViewModel extends AndroidViewModel {
    private AnimalRepository mRepository;
    private LiveData<List<AnimalEntity>> mAnimals;

    public AnimalListViewModel(@NonNull Application application) {
        super(application);

        mRepository = new AnimalRepository(application);
    }

    public LiveData<List<AnimalEntity>> getAnimals() {
        if (mAnimals == null) {
            mAnimals = mRepository.getAllAnimals();
        }

        return mAnimals;
    }

    public void insertAnimals(List<AnimalEntity> animals) {
        mRepository.insertAnimals(animals);
    }
}
