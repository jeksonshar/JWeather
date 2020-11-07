package com.jeksonshar.jweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class ViewModelFactory(private val city: Int) : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WeatherViewModel::class.java) {
            WeatherViewModel(city) as T
        } else super.create(modelClass)
    }
}