package com.jeksonshar.jweather.request;

import com.jeksonshar.jweather.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherAPI {

    @GET("/api/location/{city}")
    Call<List<WeatherModel>> getDataByCity(@Path("city") int city);

//    @GET("/api/location/search/?query={city}")
//    Call<City> getCity(@Path("city") String city);
}
