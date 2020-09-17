package com.act.map;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationM locationM);

    @Query("SELECT * FROM Location ORDER BY locId ASC")
    LiveData<List<LocationM>> getLocation();

    @Query("UPDATE Location SET locName=:name , locDesc=:desc WHERE locid = :id")
    void update(String name, String desc, Integer id);

    @Query("DELETE FROM Location WHERE locid =:id")
    void delete(Integer id);

    @Query("SELECT * FROM Location WHERE locName LIKE '%'||:search||'%' OR locDesc LIKE '%'||:search||'%' ")
    LiveData<List<LocationM>> search(String search);

    /*
    @Delete
    void deleteLoc(LocationM locationM);
    */
}
