package com.jeksonshar.jweather.repository.room;

import com.jeksonshar.jweather.model.WeatherModel;

import java.util.Date;

public class Converter {

    static WeatherEntity convert(WeatherModel weatherModel) {

        WeatherEntity weatherEntity = new WeatherEntity();

        weatherEntity.id = String.valueOf(weatherModel.getId());
        weatherEntity.date = weatherModel.getDate().getTime();
        weatherEntity.weatherStateAbbr = weatherModel.getWeatherStateAbbr();
        weatherEntity.weatherState = weatherModel.getWeatherState();
        weatherEntity.tempMax = weatherModel.getTempMax();
        weatherEntity.tempMin = weatherModel.getTempMin();
        weatherEntity.windSpeed = weatherModel.getWindSpeed();
        weatherEntity.airPressure = weatherModel.getAirPressure();
        weatherEntity.humidity = weatherModel.getHumidity();

        return weatherEntity;
    }

    static WeatherModel convert(WeatherEntity weatherEntity) {

        WeatherModel weatherModel = null;

        if (weatherEntity != null) {

            weatherModel = new WeatherModel();

            weatherModel.setId(Long.parseLong(weatherEntity.id));

            Date date = new Date();
            date.setTime(weatherEntity.date);
            weatherModel.setDate(date);

            weatherModel.setWeatherStateURL(weatherEntity.weatherStateAbbr);
            weatherModel.setWeatherState(weatherEntity.weatherState);
            weatherModel.setTempMax(weatherEntity.tempMax);
            weatherModel.setTempMin(weatherEntity.tempMin);
            weatherModel.setWindSpeed(weatherEntity.windSpeed);
            weatherModel.setAirPressure(weatherEntity.airPressure);
            weatherModel.setHumidity(weatherEntity.humidity);
        }
        return weatherModel;
    }
}
