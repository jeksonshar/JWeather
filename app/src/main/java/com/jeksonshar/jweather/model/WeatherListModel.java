package com.jeksonshar.jweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherListModel {

    @SerializedName("consolidated_weather")
    private List<WeatherModel> consolidatedWeather;

    public List<WeatherModel> getConsolidatedWeather() {return consolidatedWeather;}

}
