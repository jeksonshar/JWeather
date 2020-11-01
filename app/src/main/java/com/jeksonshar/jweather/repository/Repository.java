package com.jeksonshar.jweather.repository;

import com.jeksonshar.jweather.model.WeatherModel;

import java.util.List;

public interface Repository {

    List<WeatherModel> getAllItemsWeather();

    void saveItemsInRoom(List<WeatherModel> weatherModels);

//    WeatherModel getItemWeatherByID(int id);
//
//    void update(WeatherModel weatherModel); // ??? is need to Jweather?
}
