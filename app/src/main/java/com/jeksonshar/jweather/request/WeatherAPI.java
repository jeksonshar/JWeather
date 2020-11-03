package com.jeksonshar.jweather.request;

import com.jeksonshar.jweather.model.WeatherListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherAPI {

    @GET("/api/location/{city}")
    Call<WeatherListModel> getDataByCity(@Path("city") int city);

//    @GET("/api/location/search/?query={city}")
//    Call<City> getCity(@Path("city") String city);
}
