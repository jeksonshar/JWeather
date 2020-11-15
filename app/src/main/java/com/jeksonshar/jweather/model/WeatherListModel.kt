package com.jeksonshar.jweather.model

import com.google.gson.annotations.SerializedName
import com.jeksonshar.jweather.model.WeatherModel
//TODO data class нужно сделать, делать надо val
class WeatherListModel {
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<WeatherModel>? = null

    @SerializedName("title")
    val title: String? = null
}