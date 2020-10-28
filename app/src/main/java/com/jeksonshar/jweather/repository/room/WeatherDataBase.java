package com.jeksonshar.jweather.repository.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {WeatherEntity.class},
        version = 1,
        exportSchema = false
)

public abstract class WeatherDataBase extends RoomDatabase {
    public abstract WeatherDao getWeatherDao();
}
