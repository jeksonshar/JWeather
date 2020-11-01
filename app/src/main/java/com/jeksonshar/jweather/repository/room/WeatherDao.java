package com.jeksonshar.jweather.repository.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT*FROM WeatherEntity")
    List<WeatherEntity> getAllItemsWeather();

    @Query("SELECT*FROM WeatherEntity WHERE id == :id")
    WeatherEntity getByID(String id);

    @Insert
    void saveWeatherModel(WeatherEntity weatherEntity);

    @Update
    void updateWeatherModel(WeatherEntity entity);
}
