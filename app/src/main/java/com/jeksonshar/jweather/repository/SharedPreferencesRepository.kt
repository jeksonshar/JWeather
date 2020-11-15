package com.jeksonshar.jweather.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesRepository(context: Context) {
    var lastUpdate: String?
        get() {
            val encodedLastUpdate = sPreferences.getString(KEY_LAST_UPDATE, "")
            val gson = Gson() //TODO перенести в глобальную переменную
            val type = object : TypeToken<String?>() {}.type
            return gson.fromJson(encodedLastUpdate, type)
        }
        set(lastUpdate) {
            val gson = Gson() //TODO перенести в глобальную переменную
            sPreferences.edit().putString(KEY_LAST_UPDATE, gson.toJson(lastUpdate)).apply()
        }

    companion object {
        private const val KEY_LAST_UPDATE = "key_last_update"
        private lateinit var sPreferences: SharedPreferences //TODO убрать из статики, убрать lateinit, убрать var
    }

    init {
        sPreferences = context.getSharedPreferences("repository.pref", Context.MODE_PRIVATE)
    }
}