package com.jeksonshar.jweather.repository.room;

import android.content.Context;

import androidx.room.Room;

import com.jeksonshar.jweather.model.WeatherModel;
import com.jeksonshar.jweather.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements Repository {

    private WeatherDao mWeatherDao;

    public RoomRepository(Context context) {
        mWeatherDao = Room
                .databaseBuilder(context, WeatherDataBase.class, "weather-database1.sqLite")
                .allowMainThreadQueries()
                .build()
                .getWeatherDao();
    }

    @Override
    public List<WeatherModel> getAllItemsWeather() {
        List<WeatherEntity> weatherEntities = mWeatherDao.getAllItemsWeather();
        List<WeatherModel> weatherModels = new ArrayList<>();

        for (WeatherEntity weatherEntity: weatherEntities) {
            weatherModels.add(Converter.convert(weatherEntity));
        }
        return weatherModels;
    }

//    @Override
//    public WeatherModel getItemWeatherByID(int id) {
//        return ;
//    }
//
//    @Override
//    public void update(WeatherModel weatherModel) { // ??? is need to Jweather?
//
//    }
}
