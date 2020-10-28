package com.jeksonshar.jweather.ui;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeksonshar.jweather.model.WeatherModel;
import com.jeksonshar.jweather.request.Networking;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    int city = 922137;

    private MutableLiveData<List<WeatherModel>> mLiveData;

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

    private class InternetRequestTask extends AsyncTask<Void, Void, List<WeatherModel>> {

        @Override
        protected List<WeatherModel> doInBackground(Void... voids) {
            return executeRequest();
        }


        @Override
        protected void onPostExecute(List<WeatherModel> weatherModel) {
            mLiveData.postValue(weatherModel);
        }
    }

    private List<WeatherModel> executeRequest() {
        return Networking.makeRequestByCity(city);
    }
}
