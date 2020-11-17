package com.jeksonshar.jweather.ui

import android.os.AsyncTask
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeksonshar.jweather.model.WeatherModel
import com.jeksonshar.jweather.request.Networking
import java.util.*

class WeatherViewModel(private val city: Int) : ViewModel() {

    private lateinit var mLiveData: MutableLiveData<List<WeatherModel>>
    val request: LiveData<List<WeatherModel>>

        get() {///TODO Пока непонятно зачем тут этот код, обсудим
            mLiveData = MutableLiveData()
            setRequest()
            return mLiveData
        }

    private fun setRequest() {
        InternetRequestTask().execute()
    }

    private fun executeRequest(): List<WeatherModel>? {//TODO можно вернуть notNull
        val listWeatherModel = Networking.makeRequestByCity(city)
        return if (listWeatherModel != null) {
            lastUpdate = Calendar.getInstance()
            listWeatherModel.consolidatedWeather
        } else {
            emptyList() //TODO наверное, стоит обработать как ошибку
                    // не получается реализовать тут Toast, не пойму как получить сонтекст
        }
    }

    private inner class InternetRequestTask : AsyncTask<Void?, Void?, List<WeatherModel>?>() {
        override fun doInBackground(vararg params: Void?): List<WeatherModel>? {
            return executeRequest()
        }

        override fun onPostExecute(weatherModels: List<WeatherModel>?) {
            mLiveData.postValue(weatherModels)
        }

    }

    companion object {
        lateinit var lastUpdate: Calendar //TODO почему вынесено как статик?
                   //чтобы была возможность обращаться по имени класса и был единственным экземпляром класса
    }
}