package com.masterandroid.news_app_with_app_flavors;

public class NewsData {

    private String news_image;
    private String news_title;
    private String news_description;
    private String news_source;
    private String publishedOn;

    public NewsData() {
    }

    public NewsData(String news_image, String news_title, String news_description, String news_source,String publishedOn) {
        this.news_image = news_image;
        this.news_title = news_title;
        this.news_description = news_description;
        this.news_source = news_source;
        this.publishedOn=publishedOn;
    }

    public String getNews_description() {
        return news_description;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_description(String description) {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public String getNews_source() {
        return news_source;
    }

    public void setNews_source(String news_source) {
        this.news_source = news_source;
    }
}
