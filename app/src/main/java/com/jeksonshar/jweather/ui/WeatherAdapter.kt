package com.jeksonshar.jweather.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.jweather.R
import com.jeksonshar.jweather.model.WeatherModel

class WeatherAdapter(private val mWeatherModelList: List<WeatherModel>) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindTo(mWeatherModelList[position])
    }

    override fun getItemCount(): Int {
        return mWeatherModelList.size
    }

    init {
        setHasStableIds(true)
    }
}