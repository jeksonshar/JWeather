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
    private var mLiveData: MutableLiveData<List<WeatherModel>?>? = null//TODO может быть NotNull
    val request: LiveData<List<WeatherModel>?>//TODO может быть NotNull
        get() {///TODO Пока непонятно зачем тут этот код, обсудим
            if (mLiveData == null) {
                mLiveData = MutableLiveData()
                setRequest()
            }
            return mLiveData as MutableLiveData<List<WeatherModel>?>
        }

    @MainThread//TODO Зачем аннотация? Надо убрать
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
        }
    }

    private inner class InternetRequestTask : AsyncTask<Void?, Void?, List<WeatherModel>?>() {
        override fun doInBackground(vararg params: Void?): List<WeatherModel>? {
            return executeRequest()
        }

        override fun onPostExecute(weatherModels: List<WeatherModel>?) {
            mLiveData!!.postValue(weatherModels)
        }

    }

    companion object {
        var lastUpdate: Calendar? = null //TODO почему вынесено как статик?
    }
}