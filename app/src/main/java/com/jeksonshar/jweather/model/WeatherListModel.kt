package com.jeksonshar.jweather.model

import com.google.gson.annotations.SerializedName
import com.jeksonshar.jweather.model.WeatherModel

class WeatherListModel {
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<WeatherModel>? = null

    @SerializedName("title")
    val title: String? = null
}