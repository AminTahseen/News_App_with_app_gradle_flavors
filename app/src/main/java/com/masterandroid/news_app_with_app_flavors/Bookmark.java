package com.masterandroid.news_app_with_app_flavors;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="Bookmark_table")
public class Bookmark implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int bookmark_id;

    private String bookmark_image;
    private String bookmark_title;
    private String bookmark_description;
    private String bookmark_source;
    private String bookmark_publishedOn;
    private boolean bookmark_isBookmarked;

    public Bookmark() { }

    public Bookmark(int bookmark_id, String bookmark_image, String bookmark_title, String bookmark_description, String bookmark_source, String bookmark_publishedOn, boolean bookmark_isBookmarked) {
        this.bookmark_id = bookmark_id;
        this.bookmark_image = bookmark_image;
        this.bookmark_title = bookmark_title;
        this.bookmark_description = bookmark_description;
        this.bookmark_source = bookmark_source;
        this.bookmark_publishedOn = bookmark_publishedOn;
        this.bookmark_isBookmarked = bookmark_isBookmarked;
    }

    public int getBookmark_id() {
        return bookmark_id;
    }

    public void setBookmark_id(int bookmark_id) {
        this.bookmark_id = bookmark_id;
    }

    public String getBookmark_image() {
        return bookmark_image;
    }

    public void setBookmark_image(String bookmark_image) {
        this.bookmark_image = bookmark_image;
    }

    public String getBookmark_title() {
        return bookmark_title;
    }

    public void setBookmark_title(String bookmark_title) {
        this.bookmark_title = bookmark_title;
    }

    public String getBookmark_description() {
        return bookmark_description;
    }

    public void setBookmark_description(String bookmark_description) {
        this.bookmark_description = bookmark_description;
    }

    public String getBookmark_source() {
        return bookmark_source;
    }

    public void setBookmark_source(String bookmark_source) {
        this.bookmark_source = bookmark_source;
    }

    public String getBookmark_publishedOn() {
        return bookmark_publishedOn;
    }

    public void setBookmark_publishedOn(String bookmark_publishedOn) {
        this.bookmark_publishedOn = bookmark_publishedOn;
    }

    public boolean isBookmark_isBookmarked() {
        return bookmark_isBookmarked;
    }

    public void setBookmark_isBookmarked(boolean bookmark_isBookmarked) {
        this.bookmark_isBookmarked = bookmark_isBookmarked;
    }
}
