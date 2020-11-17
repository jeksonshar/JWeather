package com.jeksonshar.jweather.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //TODO data class, val
// с val параметры не могут быть перезаписаны в Converter, ошибка компиляции
data class WeatherEntity (

    @JvmField
    @PrimaryKey
    var id: String = "",
        @JvmField
    var date: Long = 0,
        @JvmField
    var weatherState: String = "",
        @JvmField
    var weatherStateAbbr: String = "",
        @JvmField
    var tempMin: Float = 0f,
        @JvmField
    var tempMax: Float = 0f,
        @JvmField
    var windSpeed: Float = 0f,
        @JvmField
    var airPressure: Float = 0f,
        @JvmField
    var humidity: Float = 0f
)