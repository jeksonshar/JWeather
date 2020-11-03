package com.jeksonshar.jweather.ui;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeksonshar.jweather.model.WeatherListModel;
import com.jeksonshar.jweather.model.WeatherModel;
import com.jeksonshar.jweather.request.Networking;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class WeatherViewModel extends ViewModel {

    private final int city;

    private MutableLiveData<List<WeatherModel>> mLiveData;

    private Calendar lastUpdate;

    public WeatherViewModel(int city) {
        this.city = city;
        lastUpdate = Calendar.getInstance();
    }

    LiveData<List<WeatherModel>> getRequest() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData<>();
            setRequest();
        }
        return mLiveData;
    }

    @MainThread
    private void setRequest() {
        new InternetRequestTask().execute();
    }

    private List<WeatherModel> executeRequest(){
        WeatherListModel listWeatherModel = Networking.makeRequestByCity(city);
        if (listWeatherModel != null) {
            return listWeatherModel.getConsolidatedWeather();
        }
        else  {
            return Collections.emptyList();
        }
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    private class InternetRequestTask extends AsyncTask<Void, Void, List<WeatherModel>> {

        @Override
        protected List<WeatherModel> doInBackground(Void... voids) {
            return executeRequest();
        }

        @Override
        protected void onPostExecute(List<WeatherModel> weatherModels) {
            mLiveData.postValue(weatherModels);
        }
    }
}
