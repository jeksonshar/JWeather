package com.jeksonshar.jweather.repository.room

import android.content.Context
import com.jeksonshar.jweather.model.WeatherModel
import androidx.room.Room
import com.jeksonshar.jweather.repository.Repository
import java.util.ArrayList

class RoomRepository(context: Context?) : Repository { //TODO контекст NotNull

    private val mWeatherDao: WeatherDao? = Room
            .databaseBuilder(context!!, WeatherDataBase::class.java, "weather-database5.sqLite")
            .allowMainThreadQueries() //TODO перенести на бекграунд поток
            .build() //TODO dao и бд отдельные переменные
            .weatherDao

    override val allItemsWeather: List<WeatherModel>
        get() {
            val weatherEntities = mWeatherDao?.allItemsWeather
            val weatherModels: MutableList<WeatherModel> = ArrayList()

            for (weatherEntity in weatherEntities!!) { //TODO убрать восклицательные знаки
                Converter.convert(weatherEntity)?.let { weatherModels.add(it) }
            }
            return weatherModels
        }

    override fun saveItemsInRoom(weatherModels: List<WeatherModel>?) {

        val weatherEntities: MutableList<WeatherEntity> = ArrayList()

        for (weatherModel in weatherModels!!) { //TODO убрать восклицательные знаки
            weatherEntities.add(Converter.convert(weatherModel))
        }

        if (mWeatherDao?.allItemsWeather!!.isEmpty()) { //TODO убрать восклицательные знаки
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