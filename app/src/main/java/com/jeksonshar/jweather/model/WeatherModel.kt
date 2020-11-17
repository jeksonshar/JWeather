package com.jeksonshar.jweather.model

import com.google.gson.annotations.SerializedName
import java.util.*

//TODO data class нужно сделать, делать надо val
    // с val параметры не могут быть перезаписаны в Converter, ошибка компиляции
data class WeatherModel (

    var id: Long = 0,

        @SerializedName("applicable_date")
    var date: Date? = null,

        @SerializedName("weather_state_name")
    var weatherState: String? = null,

        @SerializedName("weather_state_abbr")
    var weatherStateAbbr: String? = null,

        @SerializedName("min_temp")
    var tempMin: Float = 0f,

        @SerializedName("max_temp")
    var tempMax: Float = 0f,

        @SerializedName("wind_speed")
    var windSpeed: Float = 0f,

        @SerializedName("air_pressure")
    var airPressure: Float = 0f,

    var humidity: Float = 0f
)