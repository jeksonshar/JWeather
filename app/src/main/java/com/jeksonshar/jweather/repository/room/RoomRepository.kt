package com.jeksonshar.jweather.repository.room

import android.content.Context
import com.jeksonshar.jweather.model.WeatherModel
import androidx.room.Room
import com.jeksonshar.jweather.repository.Repository
import java.util.ArrayList

class RoomRepository(context: Context?) : Repository {

    private val mWeatherDao: WeatherDao? = Room
            .databaseBuilder(context!!, WeatherDataBase::class.java, "weather-database5.sqLite")
            .allowMainThreadQueries()
            .build()
            .weatherDao

    override val allItemsWeather: List<WeatherModel>
        get() {
            val weatherEntities = mWeatherDao?.allItemsWeather
            val weatherModels: MutableList<WeatherModel> = ArrayList()

            for (weatherEntity in weatherEntities!!) {
                Converter.convert(weatherEntity)?.let { weatherModels.add(it) }
            }
            return weatherModels
        }

    override fun saveItemsInRoom(weatherModels: List<WeatherModel>?) {

        val weatherEntities: MutableList<WeatherEntity> = ArrayList()

        for (weatherModel in weatherModels!!) {
            weatherEntities.add(Converter.convert(weatherModel))
        }

        if (mWeatherDao?.allItemsWeather!!.isEmpty()) {
            for (weatherEntity in weatherEntities) {
                mWeatherDao.saveWeatherModel(weatherEntity)
            }
        } else {
            for (weatherEntity in weatherEntities) {
                mWeatherDao.updateWeatherModel(weatherEntity)
            }
        }
    }
}