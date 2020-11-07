package com.jeksonshar.jweather.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDao {
    @get:Query("SELECT*FROM WeatherEntity")
    val allItemsWeather: List<WeatherEntity?>?

    @Query("SELECT*FROM WeatherEntity WHERE id == :id")
    fun getByID(id: String?): WeatherEntity?

    @Insert
    fun saveWeatherModel(weatherEntity: WeatherEntity?)

    @Update
    fun updateWeatherModel(weatherEntity: WeatherEntity?)
}