package com.act.map;

import android.provider.BaseColumns;

public abstract class UserContract implements BaseColumns {

    private UserContract(){}

    public static abstract class User {
        public static final String TABLE_USERS = "locationdetail";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "locationname";
        public static final String COLUMN_NAME_DESC = "locationdesc";
        public static final String COLUMN_NAME_LONG = "locationlong";
        public static final String COLUMN_NAME_LATT = "locationlatt";
        public static final String COLUMN_NAME_TIME = "locationtime";

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_NAME + " TEXT,"
                + COLUMN_NAME_DESC + " TEXT,"
               // + COLUMN_NAME_LONG + " TEXT"
               // + COLUMN_NAME_LATT + " TEXT"
               // + COLUMN_NAME_TIME + " TEXT"
                + ")";

        public static final String SQL_DROP_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_USERS;
    }


    /*
    // CODE Below added to demonstrate how multiple tables are handled in Contract Classes
    public static abstract class Admins {
        public static final String TABLE_ADMINS = "admin";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LOC = "location";
        public static final String COLUMN_NAME_TASK = "task";

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_ADMINS + "("
                + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME_NAME + " TEXT,"
                + COLUMN_NAME_LOC + " TEXT,"
                + COLUMN_NAME_TASK + " TEXT" + ")";

        public static final String SQL_DROP_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_ADMINS;
    }*/

}
