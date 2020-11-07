package com.jeksonshar.jweather.repository

import android.content.Context
import com.jeksonshar.jweather.repository.room.RoomRepository

object RepositoryProvider {

    private var instanceListWeather: Repository? = null
    private var instanceLastUpdate: SharedPreferencesRepository? = null

    fun getInstanceListWeather(context: Context?): Repository? {
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