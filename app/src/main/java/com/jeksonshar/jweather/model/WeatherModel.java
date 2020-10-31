package com.jeksonshar.jweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class WeatherModel {

    private long id;

    @SerializedName("applicable_date")
    private Date date;

    @SerializedName("weather_state_name")
    private String weatherStateName;

    @SerializedName("weather_state_abbr")
    private String weatherStateAbbr; // где выполнять запрос?

    @SerializedName("min_temp")
    private float tempMin;

    @SerializedName("max_temp")
    private float tempMax;

    @SerializedName("the_temp")
    private float tempFelt;

    @SerializedName("wind_speed")
    private float windSpeed;

    @SerializedName("air_pressure")
    private float airPressure;

    private float humidity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeatherState() {
        return weatherStateName;
    }

    public void setWeatherState(String weatherState) {
        this.weatherStateName = weatherState;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateURL(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getTempFelt() {
        return tempFelt;
    }

    public void setTempField(float tempFelt) {
        this.tempFelt = tempFelt;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(float airPressure) {
        this.airPressure = airPressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
