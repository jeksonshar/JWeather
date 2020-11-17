package com.jeksonshar.jweather.repository

import android.content.Context
import com.jeksonshar.jweather.repository.room.RoomRepository
//TODO лушче сделать application статикой и оттуда тянуть твои зависимости
object RepositoryProvider {

    private var instanceListWeather: RepositoryDataBase? = null
    private var instanceLastUpdate: SharedPreferencesRepository? = null

    fun getInstanceListWeather(context: Context?): RepositoryDataBase? {
        if (instanceListWeather == null) {
            instanceListWeather = RoomRepository(context)
        }
        return instanceListWeather
    }

    fun getInstanceLastUpdate(context: Context?): SharedPreferencesRepository? {
        if (instanceLastUpdate == null) {
            instanceLastUpdate = SharedPreferencesRepository(context!!)
        }
        return instanceLastUpdate
    }
}