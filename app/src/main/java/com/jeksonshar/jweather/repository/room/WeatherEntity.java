package com.jeksonshar.jweather.repository.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeatherEntity {

    @PrimaryKey
    @NonNull
    public String id;
    public String date;
    public String weatherState;
    public String weatherStateURL;
    public String tempMin;
    public String tempMax;
    public String tempFelt;
    public String windSpeed;
    public String airPressure;
    public String humidity;
}
