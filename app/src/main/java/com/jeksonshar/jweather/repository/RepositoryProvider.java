package com.jeksonshar.jweather.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.jeksonshar.jweather.repository.room.RoomRepository;

public class RepositoryProvider {

    private static Repository instanceListWeather;

    private static SharedPreferencesRepository instanceLastUpdate;

    private RepositoryProvider() {
    }

    public static Repository getInstanceListWeather(Context context) {
        if (instanceListWeather == null) {
            instanceListWeather = new RoomRepository(context);
        }
        return instanceListWeather;
    }

    public static SharedPreferencesRepository getInstanceLastUpdate(Context context) {
        if (instanceLastUpdate == null) {
            instanceLastUpdate = new SharedPreferencesRepository(context);
        }
        return instanceLastUpdate;
    }
}
