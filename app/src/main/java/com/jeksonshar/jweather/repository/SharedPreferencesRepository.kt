package com.jeksonshar.jweather.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesRepository(context: Context) {

    private val gSon = Gson()
    private val sPreferences: SharedPreferences = context.getSharedPreferences("repository.pref", Context.MODE_PRIVATE)

    var lastUpdate: String?
        get() {
            val encodedLastUpdate = sPreferences.getString(KEY_LAST_UPDATE, "")
            val type = object : TypeToken<String?>() {}.type
            return gSon.fromJson(encodedLastUpdate, type)
        }
        set(lastUpdate) {
            sPreferences.edit().putString(KEY_LAST_UPDATE, gSon.toJson(lastUpdate)).apply()
        }

    companion object {
        private const val KEY_LAST_UPDATE = "key_last_update"
    }
}