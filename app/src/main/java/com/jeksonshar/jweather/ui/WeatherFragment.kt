package com.jeksonshar.jweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    private var city = 922137 // Kharkiv //TODO перенести во вьюмодель(бизнес логика)
    private var mRecyclerView: RecyclerView? = null
    private var mWeatherViewModel: WeatherViewModel? = null //TODO ViewModel должен быть NotNull
    private var mWeatherAdapter: WeatherAdapter? = null//TODO непонятно зачем адаптер глобальное поле, стоит убрать
    private var lastUpdateView: TextView? = null
    private var changeView: ImageButton? = null
    private var mWeatherModels: List<WeatherModel>? = null //TODO непонятно зачем список погоды глобальное поле, стоит убрать
    private var mRepository: Repository? = null //TODO репозиторий во вьюмодель нужно перенести и обращаться к нему через viewModel
    private var lastUpdate: String = "" //TODO ненужное глобальная переменная, можно сделать локальной переменной

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //TODO Это зачем? У тебя нет меню в приложении
        mRepository = RepositoryProvider.getInstanceListWeather(context)
        mWeatherViewModel = ViewModelProvider(this, ViewModelFactory(city))
                .get(WeatherViewModel::class.java)

//        new InternetRequestTask().execute();    // do it if not using ViewModel - пока не удалять //TODO закомментированный код нужно удалять
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lastUpdateView = view.findViewById(R.id.last_update_view) // TODO переделать на синтетики или на ViewBinding обращения к полям
        mRecyclerView = view.findViewById(R.id.recyclerView)
        changeView = view.findViewById(R.id.fab)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {//TODO логику с полями вью фрагмента лучше перенести в onViewCreated
        super.onActivityCreated(savedInstanceState)

        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        mWeatherModels = RepositoryProvider.getInstanceListWeather(context)?.allItemsWeather //TODO не видно зависимости в классе, обсудим на звонке

        if (mWeatherModels != null) {
            mWeatherAdapter = WeatherAdapter(mWeatherModels!!)
            mRecyclerView!!.adapter = mWeatherAdapter
        }

        lastUpdate = RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate!! //TODO пофиксить NPE (обращение с !!)
        lastUpdateView!!.text = lastUpdate
        mWeatherViewModel!!.request.observe(viewLifecycleOwner, Observer { weatherModels ->
            mWeatherModels = weatherModels
            if (mWeatherModels!!.isNotEmpty()) {//TODO в данном случае из viewModel нужно присылать notnull список
                val dateFormat = SimpleDateFormat(
                        " d MMM yyyy; HH:mm", Locale.getDefault())
                lastUpdate = dateFormat.format(WeatherViewModel.lastUpdate!!.time) //TODO непонятная логика с lastUpdate, надо убрать
                lastUpdateView!!.text = lastUpdate
                RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate = lastUpdate
                mRepository!!.saveItemsInRoom(mWeatherModels)
            } else {
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG)
                        .show() //TODO текст лучше вынести во вьюмодель
            }
            mWeatherAdapter = RepositoryProvider //TODO обращение к репозиторию через viewModel
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