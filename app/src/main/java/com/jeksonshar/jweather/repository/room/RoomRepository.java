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
                .databaseBuilder(context, WeatherDataBase.class, "weather-database4.sqLite")
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

    @Override
    public void saveItemsInRoom(List<WeatherModel> weatherModels) {
        List<WeatherEntity> weatherEntities = new ArrayList<>();

        for (WeatherModel weatherModel: weatherModels) {
            weatherEntities.add(Converter.convert(weatherModel));
        }

        if (mWeatherDao.getAllItemsWeather().isEmpty()) {
            for (WeatherEntity weatherEntity: weatherEntities) {
                mWeatherDao.saveWeatherModel(weatherEntity);
            }
        } else {
            for (WeatherEntity weatherEntity : weatherEntities) {
                mWeatherDao.updateWeatherModel(weatherEntity);
            }
        }
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
