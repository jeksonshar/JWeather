package com.jeksonshar.jweather.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeksonshar.jweather.R;
import com.jeksonshar.jweather.model.WeatherModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private final TextView date;
    private final ImageView weatherStateImage;
    private final TextView temp;
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
        windSpeed = itemView.findViewById(R.id.wind_speed);
        airPressure = itemView.findViewById(R.id.air_pressure);
        humidity = itemView.findViewById(R.id.humidity);
        weatherState = itemView.findViewById(R.id.weather_state);
    }

    public void bindTo(WeatherModel weatherModel) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEEE\nd MMM yyyy", Locale.getDefault());
        date.setText(dateFormat.format(weatherModel.getDate()));

        Picasso.get()
                .load("https://www.metaweather.com/static/img/weather/png/" + weatherModel.getWeatherStateAbbr() + ".png")
                .into(weatherStateImage);

        String minMaxTemp = Math.round(weatherModel.getTempMax()) + " / " + Math.round(weatherModel.getTempMin()) + " ˚C";
        temp.setText(minMaxTemp);

        float wind = weatherModel.getWindSpeed();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String windSpeed = decimalFormat.format(wind*0.44704) + " м/с"; // convert from mph to m/s
        this.windSpeed.setText(windSpeed);

        float pressure = weatherModel.getAirPressure();
        String airPressure = Math.round(pressure/1.33322) + " мм"; // convert from mBar to mmHg
        this.airPressure.setText(airPressure);

        String humidity = Math.round(weatherModel.getHumidity()) + " %";
        this.humidity.setText(humidity);

        weatherState.setText(weatherModel.getWeatherState());
    }
}
