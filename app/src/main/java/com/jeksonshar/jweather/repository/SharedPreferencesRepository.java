package com.jeksonshar.jweather.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SharedPreferencesRepository {

    private static final String KEY_LAST_UPDATE ="key_last_update";
    private static SharedPreferences sPreferences;

    public SharedPreferencesRepository(Context context) {
        sPreferences = context.getSharedPreferences("repository.pref", Context.MODE_PRIVATE);
    }

    public String getLastUpdate() {
        String encodedLastUpdate = sPreferences.getString(KEY_LAST_UPDATE, "");

        Gson gson = new Gson();
        Type type = new TypeToken<String>() {}.getType();

        return gson.fromJson(encodedLastUpdate, type);
    }

    public void setLastUpdate(String lastUpdate) {
        Gson gson = new Gson();
        sPreferences.edit().putString(KEY_LAST_UPDATE, gson.toJson(lastUpdate)).apply();
    }
}
