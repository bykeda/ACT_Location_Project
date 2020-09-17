package com.act.map;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocationM.class}, version = 1, exportSchema = false)
public abstract class LocationRoomDB extends RoomDatabase {

    public abstract LocationDAO locationDAO();

    private static volatile LocationRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LocationRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocationRoomDB.class, "actlocation").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

             // If you want to keep data through app restarts,
            // comment out the following block
      /*      databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
               LocationDAO dao = INSTANCE.locationDAO();
                dao.deleteAll();

              // Word word = new Word("Hello");
                //dao.insert(word);
                //word = new Word("World");
                //dao.insert(word);
            });*/
        }
    };
}

