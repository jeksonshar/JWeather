package com.jeksonshar.jweather.repository.room

import com.jeksonshar.jweather.model.WeatherModel
import java.util.*

object Converter {
    fun convert(weatherModel: WeatherModel): WeatherEntity {

        val weatherEntity = WeatherEntity()

        weatherEntity.id = weatherModel.id.toString()
        weatherEntity.date = weatherModel.date!!.time
        weatherEntity.weatherStateAbbr = weatherModel.weatherStateAbbr!!
        weatherEntity.weatherState = weatherModel.weatherState!!
        weatherEntity.tempMax = weatherModel.tempMax
        weatherEntity.tempMin = weatherModel.tempMin
        weatherEntity.windSpeed = weatherModel.windSpeed
        weatherEntity.airPressure = weatherModel.airPressure
        weatherEntity.humidity = weatherModel.humidity

        return weatherEntity
    }

    fun convert(weatherEntity: WeatherEntity?): WeatherModel? {

        var weatherModel: WeatherModel? = null

        if (weatherEntity != null) {

            weatherModel = WeatherModel()

            weatherModel.id = weatherEntity.id.toLong()

            val date = Date()
            date.time = weatherEntity.date
            weatherModel.date = date

            weatherModel.setWeatherStateURL(weatherEntity.weatherStateAbbr)
            weatherModel.weatherState = weatherEntity.weatherState
            weatherModel.tempMax = weatherEntity.tempMax
            weatherModel.tempMin = weatherEntity.tempMin
            weatherModel.windSpeed = weatherEntity.windSpeed
            weatherModel.airPressure = weatherEntity.airPressure
            weatherModel.humidity = weatherEntity.humidity
        }
        return weatherModel
    }
}