package com.jeksonshar.jweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.jweather.R
import com.jeksonshar.jweather.model.WeatherModel
import com.jeksonshar.jweather.repository.Repository
import com.jeksonshar.jweather.repository.RepositoryProvider
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {
    private var city = 922137 // Kharkiv
    private var mRecyclerView: RecyclerView? = null
    private var mWeatherViewModel: WeatherViewModel? = null
    private var mWeatherAdapter: WeatherAdapter? = null
    private var lastUpdateView: TextView? = null
    private var changeView: ImageButton? = null
    private var mWeatherModels: List<WeatherModel>? = null
    private var mRepository: Repository? = null
    private var lastUpdate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mRepository = RepositoryProvider.getInstanceListWeather(context)
        mWeatherViewModel = ViewModelProvider(this, ViewModelFactory(city))
                .get(WeatherViewModel::class.java)

//        new InternetRequestTask().execute();    // do it if not using ViewModel - пока не удалять
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lastUpdateView = view.findViewById(R.id.last_update_view)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        changeView = view.findViewById(R.id.fab)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        mWeatherModels = RepositoryProvider.getInstanceListWeather(context)?.allItemsWeather

        if (mWeatherModels != null) {
            mWeatherAdapter = WeatherAdapter(mWeatherModels!!)
            mRecyclerView!!.adapter = mWeatherAdapter
        }

        lastUpdate = RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate!!
        lastUpdateView!!.text = lastUpdate
        mWeatherViewModel!!.request.observe(viewLifecycleOwner, { weatherModels ->
            mWeatherModels = weatherModels
            if (mWeatherModels!!.isNotEmpty()) {
                val dateFormat = SimpleDateFormat(
                        " d MMM yyyy; HH:mm", Locale.getDefault())
                lastUpdate = dateFormat.format(WeatherViewModel.lastUpdate!!.time)
                lastUpdateView!!.text = lastUpdate
                RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate = lastUpdate
                mRepository!!.saveItemsInRoom(mWeatherModels)
            } else {
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG)
                        .show()
            }
            mWeatherAdapter = RepositoryProvider
                    .getInstanceListWeather(context)
                    ?.allItemsWeather?.let { WeatherAdapter(it) }
            mRecyclerView!!.adapter = mWeatherAdapter
        })

        changeView!!.setOnClickListener {
            //TODO
        }
    }

    //          // Do what is below if not using ViewModel - пока не удалять
    //    @MainThread
    //    private void applyItemWeathers() {
    //        mWeatherAdapter = new WeatherAdapter(RepositoryProvider.getInstance(getContext()).getAllItemsWeather());
    //        mRecyclerView.setAdapter(mWeatherAdapter);
    //    }
    //
    //    @WorkerThread
    //    private List<WeatherModel> executeRequest(){
    //        WeatherListModel listWeatherModel = Networking.makeRequestByCity(city);
    //        if (listWeatherModel != null) {
    //            return listWeatherModel.getConsolidatedWeather();
    //        }
    //        else  {
    //            return Collections.emptyList();
    //        }
    //    }
    //
    //    private class InternetRequestTask extends AsyncTask<Void, Void, List<WeatherModel>> {
    //
    //        @Override
    //        protected List<WeatherModel> doInBackground(Void... voids) {
    //            return executeRequest();
    //        }
    //
    //        @Override
    //        protected void onPostExecute(List<WeatherModel> weatherModels) {
    //            if (!weatherModels.isEmpty()) {
    //                mRepository.saveItemsInRoom(weatherModels);
    //            } else {
    //                try {
    //                    Toast.makeText(getActivity(), "No Internet connection", Toast.LENGTH_LONG).show();
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //            applyItemWeathers();
    //        }
    //    }
    //    //
}