package com.jeksonshar.jweather.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeksonshar.jweather.R;
import com.jeksonshar.jweather.model.WeatherModel;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<WeatherModel> mWeatherModelList;

    public WeatherAdapter(List<WeatherModel> weatherModelList) {
        mWeatherModelList = weatherModelList;

        setHasStableIds(true);
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.bindTo(mWeatherModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherModelList.size();
    }
}
