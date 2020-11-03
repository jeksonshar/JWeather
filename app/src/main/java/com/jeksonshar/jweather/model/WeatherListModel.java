package com.jeksonshar.jweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherListModel {

    @SerializedName("consolidated_weather")
    private List<WeatherModel> consolidatedWeather;

    @SerializedName("title")
    private String title;

    public List<WeatherModel> getConsolidatedWeather() {return consolidatedWeather;}

    public String getTitle() {
        return title;
    }

}
