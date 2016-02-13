package com.kokhouser.napod.api;

import com.kokhouser.napod.models.Astropic;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by hkok on 7/28/2015.
 * Retrofit API Interface for the NASA APOD API
 */
public interface APODAPIInterface {
    @GET("/planetary/apod")
    void getPictureWithKey(@Query("api_key")String apiKey, Callback<Astropic> cb);

    @GET("/planetary/apod")
    void getPictureWithKeyAndDate(@Query("api_key")String apiKey, @Query("date")String date,
                                  Callback<Astropic> cb);
}
