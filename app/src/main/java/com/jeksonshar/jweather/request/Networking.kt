package com.jeksonshar.jweather.request

import com.jeksonshar.jweather.model.WeatherListModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object Networking {
    fun makeRequestByCity(city: Int): WeatherListModel? {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.metaweather.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val weatherAPI = retrofit.create(WeatherAPI::class.java)
        val getWeatherByCity = weatherAPI.getDataByCity(city)
        return try {
            getWeatherByCity!!.execute().body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    //    public static City makeRequestToGetCity(String city) {
    //        Retrofit retrofit = new Retrofit.Builder()
    //                .baseUrl("https://www.metaweather.com")
    //                .addConverterFactory(GsonConverterFactory.create())
    //                .build();
    //
    //        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
    //        Call<City> getCity = weatherAPI.getCity(city);
    //
    //        try {
    //            return getCity.execute().body();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //            return null;
    //        }
    //    }
}