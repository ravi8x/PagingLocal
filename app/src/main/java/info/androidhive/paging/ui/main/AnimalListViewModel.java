package info.androidhive.paging.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

import info.androidhive.paging.app.Constants;
import info.androidhive.paging.db.entity.AnimalEntity;

public class AnimalListViewModel extends AndroidViewModel {
    private AnimalRepository mRepository;
    private LiveData<PagedList<AnimalEntity>> mAnimals;

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build();

    public AnimalListViewModel(@NonNull Application application) {
        super(application);

        mRepository = new AnimalRepository(application);
    }

    public LiveData<PagedList<AnimalEntity>> getAnimals() {
        if (mAnimals == null) {
            mAnimals = mRepository.getAllAnimals(config);
        }
        return mAnimals;
    }

    public void insertSampleData() {
        mRepository.insertSampleAnimals();
    }

    public void insertAnimals(List<AnimalEntity> animals) {
        mRepository.insertAnimals(animals);
    }

    public AnimalEntity getAnimal(int id) throws ExecutionException, InterruptedException {
        return mRepository.getAnimal(id);
    }

    public void updateAnimal(AnimalEntity animal) {
        mRepository.updateAnimal(animal);
    }
}
