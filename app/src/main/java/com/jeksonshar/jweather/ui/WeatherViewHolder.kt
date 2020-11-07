package com.jeksonshar.jweather.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.jweather.R
import com.jeksonshar.jweather.model.WeatherModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val date: TextView = itemView.findViewById(R.id.date_item_weather)
    private val weatherStateImage: ImageView = itemView.findViewById(R.id.view_weather_state)
    private val temp: TextView = itemView.findViewById(R.id.max_min_temp)
    private val windSpeed: TextView = itemView.findViewById(R.id.wind_speed)
    private val airPressure: TextView = itemView.findViewById(R.id.air_pressure)
    private val humidity: TextView = itemView.findViewById(R.id.humidity)
    private val weatherState: TextView = itemView.findViewById(R.id.weather_state)
    fun bindTo(weatherModel: WeatherModel) {
        val dateFormat = SimpleDateFormat(
                "EEEE\nd MMM yyyy", Locale.getDefault())
        date.text = dateFormat.format(weatherModel.date!!)
        Picasso.get()
                .load("https://www.metaweather.com/static/img/weather/png/" + weatherModel.weatherStateAbbr + ".png")
                .into(weatherStateImage)
        val minMaxTemp = weatherModel.tempMax.roundToInt().toString() + " / " + weatherModel.tempMin.roundToInt() + " ˚C"
        temp.text = minMaxTemp
        val wind = weatherModel.windSpeed
        val decimalFormat = DecimalFormat("#.#")
        val windSpeed = decimalFormat.format(wind * 0.44704) + " м/с" // convert from mph to m/s
        this.windSpeed.text = windSpeed
        val pressure = weatherModel.airPressure
        val airPressure = (pressure / 1.33322).roundToInt().toString() + " мм" // convert from mBar to mmHg
        this.airPressure.text = airPressure
        val humidity = weatherModel.humidity.roundToInt().toString() + " %"
        this.humidity.text = humidity
        weatherState.text = weatherModel.weatherState
    }

}