package com.act.map;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class LocationRepository {

    private LocationDAO mLocationDAO;
    private LiveData<List<LocationM>> mAllLocation;

    public LocationRepository(Application application) {
        LocationRoomDB db = LocationRoomDB.getDatabase(application);
        mLocationDAO = db.locationDAO();
        mAllLocation = mLocationDAO.getLocation();
    }

    public LiveData<List<LocationM>> getAllLocation() {
        return mAllLocation;
    }

    public void insert(LocationM locationM) {
        LocationRoomDB.databaseWriteExecutor.execute(() -> {
            mLocationDAO.insert(locationM);
        });
    }

    public void update(LocationM locationM) {
        LocationRoomDB.databaseWriteExecutor.execute(() -> {
            mLocationDAO.update(locationM.getLocName(), locationM.getLocDesc(), locationM.getLocId());
        });
    }

    public void delete(LocationM locationM) {
        LocationRoomDB.databaseWriteExecutor.execute(() -> {
            mLocationDAO.delete(locationM.getLocId());
        });
    }

    public LiveData<List<LocationM>> search(String search) {

        mAllLocation = mLocationDAO.search(search);

        return mAllLocation;
    }
}
