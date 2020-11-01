package com.jeksonshar.jweather.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeksonshar.jweather.R;
import com.jeksonshar.jweather.model.WeatherListModel;
import com.jeksonshar.jweather.model.WeatherModel;
import com.jeksonshar.jweather.repository.Repository;
import com.jeksonshar.jweather.repository.RepositoryProvider;
import com.jeksonshar.jweather.request.Networking;

import java.util.Collections;
import java.util.List;

public class WeatherFragment extends Fragment {

    int city = 922137; // Kharkiv

    private RecyclerView mRecyclerView;
    private WeatherViewModel mWeatherViewModel;
    private WeatherAdapter mWeatherAdapter;
    private ImageButton changeView;

    private List<WeatherModel> mWeatherModels;
    private Repository mRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mRepository = RepositoryProvider.getInstance(getContext());

        new InternetRequestTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        changeView = view.findViewById(R.id.fab);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWeatherModels = RepositoryProvider.getInstance(getActivity()).getAllItemsWeather();
        if (mWeatherModels != null) {
            mWeatherAdapter = new WeatherAdapter(mWeatherModels);
            mRecyclerView.setAdapter(mWeatherAdapter);
        }

        changeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    @MainThread
    private void applyItemWeathers() {
        mWeatherAdapter = new WeatherAdapter(RepositoryProvider.getInstance(getContext()).getAllItemsWeather());
        mRecyclerView.setAdapter(mWeatherAdapter);
    }

    @WorkerThread
    private List<WeatherModel> executeRequest(){
        WeatherListModel listWeatherModel = Networking.makeRequestByCity(city);
        if (listWeatherModel != null) {
            return listWeatherModel.getConsolidatedWeather();
        }
        else  {
            return Collections.emptyList();
        }
    }

    private class InternetRequestTask extends AsyncTask<Void, Void, List<WeatherModel>> {

        @Override
        protected List<WeatherModel> doInBackground(Void... voids) {
            return executeRequest();
        }

        @Override
        protected void onPostExecute(List<WeatherModel> weatherModels) {
            if (!weatherModels.isEmpty()) {
                mRepository.saveItemsInRoom(weatherModels);
            } else {
                try {
                    Toast.makeText(getActivity(), "No Internet connection", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            applyItemWeathers();
        }
    }
}
