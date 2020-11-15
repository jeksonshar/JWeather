package com.jeksonshar.jweather.model

import com.google.gson.annotations.SerializedName
import java.util.*

//TODO data class нужно сделать, делать надо val
class WeatherModel {
    var id: Long = 0

    @SerializedName("applicable_date")
    var date: Date? = null

    @SerializedName("weather_state_name")
    var weatherState: String? = null

    @SerializedName("weather_state_abbr")
    var weatherStateAbbr // где выполнять запрос?
            : String? = null
        private set

    @SerializedName("min_temp")
    var tempMin = 0f

    @SerializedName("max_temp")
    var tempMax = 0f

    @SerializedName("wind_speed")
    var windSpeed = 0f

    @SerializedName("air_pressure")
    var airPressure = 0f
    var humidity = 0f

    //TODO В pojo классе не доллжно быть лишних сеттеров
    fun setWeatherStateURL(weatherStateAbbr: String?) {
        this.weatherStateAbbr = weatherStateAbbr
    }
}