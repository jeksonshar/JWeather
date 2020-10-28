package com.jeksonshar.jweather.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeksonshar.jweather.R;
import com.jeksonshar.jweather.model.WeatherModel;
import com.squareup.picasso.Picasso;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private final TextView date;
    private final ImageView weatherStateImage;
    private final TextView temp;
    private final TextView feltTemp;
    private final TextView windSpeed;
    private final TextView airPressure;
    private final TextView humidity;
    private final TextView weatherState;

    private WeatherModel currantItemWeather;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date_item_weather);
        weatherStateImage = itemView.findViewById(R.id.view_weather_state);
        temp = itemView.findViewById(R.id.max_min_temp);
        feltTemp = itemView.findViewById(R.id.felt_temp);
        windSpeed = itemView.findViewById(R.id.wind_speed);
        airPressure = itemView.findViewById(R.id.air_pressure);
        humidity = itemView.findViewById(R.id.humidity);
        weatherState = itemView.findViewById(R.id.weather_state);
    }

    public void bindTo(WeatherModel weatherModel) {

        date.setText(weatherModel.getDate());
        Picasso.get().load(weatherModel.getWeatherStateURL()).into(weatherStateImage);
        String minMaxTemp = weatherModel.getTempMax() + "/" + weatherModel.getTempMin();
        temp.setText(minMaxTemp);
        feltTemp.setText(weatherModel.getTempFelt());
        windSpeed.setText(weatherModel.getWindSpeed());
        airPressure.setText(weatherModel.getAirPressure());
        humidity.setText(weatherModel.getHumidity());
        weatherState.setText(weatherModel.getWeatherState());
    }
}
