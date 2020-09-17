package com.act.map;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {

    private LocationRepository mRepository;
    private LiveData<List<LocationM>> mAllLocation;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LocationRepository(application);
        mAllLocation = mRepository.getAllLocation();
    }

    LiveData<List<LocationM>> getAllLocation() {
        return mAllLocation;
    }

    LiveData<List<LocationM>> search(String search) {
        Application app = new Application();
        //super(app);
        mRepository = new LocationRepository(app);
        mAllLocation = mRepository.search(search);

        return mAllLocation;
    }

    public void insert(LocationM locationM) {
        mRepository.insert(locationM);
    }

    public void update(LocationM locationM) {
        mRepository.update(locationM);
    }

    public void delete(LocationM locationM) {
        mRepository.delete(locationM);
    }


}
