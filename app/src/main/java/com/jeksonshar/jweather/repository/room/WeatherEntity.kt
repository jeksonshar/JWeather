package com.jeksonshar.jweather.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //TODO data class, val
class WeatherEntity {

    @JvmField
    @PrimaryKey
    var id: String = ""
    @JvmField
    var date: Long = 0
    @JvmField
    var weatherState: String = ""
    @JvmField
    var weatherStateAbbr: String = ""
    @JvmField
    var tempMin = 0f
    @JvmField
    var tempMax = 0f
    @JvmField
    var windSpeed = 0f
    @JvmField
    var airPressure = 0f
    @JvmField
    var humidity = 0f
}