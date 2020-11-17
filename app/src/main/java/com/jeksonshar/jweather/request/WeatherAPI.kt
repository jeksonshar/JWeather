package com.jeksonshar.jweather.request

import com.jeksonshar.jweather.model.WeatherListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {

    @GET("/api/location/{city}")
    fun getDataByCity(@Path("city") city: Int): Call<WeatherListModel>

    //    @GET("/api/location/search/?query={city}")
    //    Call<City> getCity(@Path("city") String city);
}