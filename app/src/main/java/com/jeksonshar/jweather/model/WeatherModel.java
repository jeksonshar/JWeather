package com.jeksonshar.jweather.model;

public class WeatherModel {

    private long id;
    private String date;
    private String weatherState;
    private String weatherStateURL;
    private String tempMin;
    private String tempMax;
    private String tempFelt;
    private String windSpeed;
    private String airPressure;
    private String humidity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    public String getWeatherStateURL() {
        return weatherStateURL;
    }

    public void setWeatherStateURL(String weatherStateURL) {
        this.weatherStateURL = weatherStateURL;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempFelt() {
        return tempFelt;
    }

    public void setTempField(String tempFelt) {
        this.tempFelt = tempFelt;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
