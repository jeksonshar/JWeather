package com.jeksonshar.jweather.request;

import com.jeksonshar.jweather.model.WeatherListModel;
import com.jeksonshar.jweather.model.WeatherModel;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networking {

    public static WeatherListModel makeRequestByCity(int city) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        Call<WeatherListModel> getWeatherByCity = weatherAPI.getDataByCity(city);

        try {
            return getWeatherByCity.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
