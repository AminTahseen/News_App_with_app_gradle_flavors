package com.masterandroid.news_app_with_app_flavors;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bookmark.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static  String databaseName="NewsApp_DB";

    public synchronized static RoomDB getInstance(Context context){
        if(database==null)
        {
            database= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract MainDAO mainDAO();
}
