package com.jeksonshar.jweather.repository

import com.jeksonshar.jweather.model.WeatherModel

interface Repository { //TODO слишком общее название, это интерфейс работает только с бд

    val allItemsWeather: List<WeatherModel>?

    fun saveItemsInRoom(weatherModels: List<WeatherModel>?)

    //    WeatherModel getItemWeatherByID(int id);
    //
    //    void update(WeatherModel weatherModel); // ??? is need to Jweather?
}