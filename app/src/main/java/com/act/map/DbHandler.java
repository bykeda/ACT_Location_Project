package com.act.map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "location.db";

    public DbHandler(View.OnClickListener context) {
        super((Context) context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.User.SQL_CREATE_ENTRIES);
        //Code Below Not Used
        //db.execSQL(UserContract.Admins.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserContract.User.SQL_DROP_ENTRIES);
        //Code Below Not Used
        //db.execSQL(UserContract.Admins.SQL_DROP_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String locname, String locdesc) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(UserContract.User.COLUMN_NAME_NAME, locname);
        cValues.put(UserContract.User.COLUMN_NAME_DESC, locdesc);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserContract.User.TABLE_USERS, null, cValues);
        db.close();
    }


    /*

    // Get User Details
    public ArrayList< HashMap<String, String> > GetUsers() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        String[] projection = {
                UserContract.User.COLUMN_NAME_NAME,
                UserContract.User.COLUMN_NAME_LOC,
                UserContract.User.COLUMN_NAME_DESG
        };

        Cursor cursor = db.query(
                UserContract.User.TABLE_USERS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

       if( cursor.moveToFirst()) {
           do {
               HashMap <String, String> user = new HashMap<>();
               user.put("name", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_NAME)));
               user.put("designation", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_DESG)));
               user.put("location", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_LOC)));
               userList.add(user);
           } while (cursor.moveToNext());
       }
        return userList;
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        String[] projection = {
                UserContract.User.COLUMN_NAME_NAME,
                UserContract.User.COLUMN_NAME_LOC,
                UserContract.User.COLUMN_NAME_DESG
        };
        String selection = UserContract.User.COLUMN_NAME_ID + "=?" ;
        String[] selectionArgs = {
                String.valueOf(userid)
        };
        Cursor cursor = db.query(
                UserContract.User.TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_DESG)));
            user.put("location", cursor.getString(cursor.getColumnIndex(UserContract.User.COLUMN_NAME_LOC)));
            userList.add(user);
        }
        return userList;
    }

    // Delete User Details
    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                UserContract.User.TABLE_USERS,
                UserContract.User.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(userid)});
        db.close();
    }

    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(UserContract.User.COLUMN_NAME_LOC, location);
        cVals.put(UserContract.User.COLUMN_NAME_DESG, designation);
        int count = db.update(
                UserContract.User.TABLE_USERS,
                cVals,
                UserContract.User.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(id)}
                );
        return count;
    }*/
}