package com.jeksonshar.jweather.repository.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeatherEntity {

    @PrimaryKey
    @NonNull
    public String id;
    public long date;
    public String weatherState;
    public String weatherStateAbbr;
    public float tempMin;
    public float tempMax;
    public float windSpeed;
    public float airPressure;
    public float humidity;
}
