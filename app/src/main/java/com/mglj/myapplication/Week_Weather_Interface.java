package com.mglj.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Week_Weather_Interface {

    @GET("/data/2.5/forecast")
    Call<WeekRepo> get_weather(@Query("appid") String appid, @Query("lat") double lat, @Query("lon") double lon);

}
