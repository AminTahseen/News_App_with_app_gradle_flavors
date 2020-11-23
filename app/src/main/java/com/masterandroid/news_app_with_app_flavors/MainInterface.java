package com.masterandroid.news_app_with_app_flavors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainInterface {

    @GET("v2/everything")
    Call<String>STRING_CALL(@Query("q") String tag, @Query("page") int page, @Query("pageSize") int limit, @Query("apiKey") String key);
}
