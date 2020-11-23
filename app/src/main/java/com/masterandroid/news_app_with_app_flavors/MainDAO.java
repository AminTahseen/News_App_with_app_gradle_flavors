package com.masterandroid.news_app_with_app_flavors;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDAO {

    @Insert(onConflict = REPLACE)
    void Insert(Bookmark data);

    @Delete
    void Delete(Bookmark data);

    @Query("SELECT * FROM bookmark_table")
    List<Bookmark> getAllBookmarks();


}
