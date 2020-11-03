package com.jeksonshar.jweather.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int city;

    public ViewModelFactory(int city) {
        super();
        this.city = city;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == WeatherViewModel.class) {
            return (T) new WeatherViewModel(city);
        }
        return super.create(modelClass);
    }
}
