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
import com.jeksonshar.jweather.repository.RepositoryDataBase
import com.jeksonshar.jweather.repository.RepositoryProvider
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private var city = 922137 // Kharkiv //TODO перенести во вьюмодель(бизнес логика)
                                        // планировал сделать запрос на получения номера вводимого города, выбирать город
    private var mRecyclerView: RecyclerView? = null
    private var mWeatherViewModel: WeatherViewModel? = null //TODO ViewModel должен быть NotNull
    private var lastUpdateView: TextView? = null
    private var changeView: ImageButton? = null
    private var mRepository: RepositoryDataBase? = null //TODO репозиторий во вьюмодель нужно перенести и обращаться к нему через viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRepository = RepositoryProvider.getInstanceListWeather(context)
        mWeatherViewModel = ViewModelProvider(this, ViewModelFactory(city))
                .get(WeatherViewModel::class.java)
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

        var mWeatherAdapter: WeatherAdapter?
        var mWeatherModels: List<WeatherModel>?

        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        mWeatherModels = RepositoryProvider.getInstanceListWeather(context)?.allItemsWeather //TODO не видно зависимости в классе, обсудим на звонке

        if (mWeatherModels != null) {
            mWeatherAdapter = WeatherAdapter(mWeatherModels)
            mRecyclerView!!.adapter = mWeatherAdapter
        }

        var lastUpdate: String? = RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate
        lastUpdateView!!.text = lastUpdate
        mWeatherViewModel!!.request.observe(viewLifecycleOwner, Observer { weatherModels ->
            mWeatherModels = weatherModels

            if (mWeatherModels!!.isNotEmpty()) {//TODO в данном случае из viewModel нужно присылать notnull список
                val dateFormat = SimpleDateFormat(
                        " d MMM yyyy; HH:mm", Locale.getDefault())
                lastUpdate = dateFormat.format(WeatherViewModel.lastUpdate.time) //TODO непонятная логика с lastUpdate, надо убрать
                                         // в случае, если ответ на запрос получен, во вьюМодел получаем дату и время и форматируем
                lastUpdateView!!.text = lastUpdate
                RepositoryProvider.getInstanceLastUpdate(context)?.lastUpdate = lastUpdate
                mRepository!!.saveItemsInRoom(mWeatherModels)
            } else {
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG)
                        .show() //TODO текст лучше вынести во вьюмодель
            }                   // не получается реализовать Toast в ViewModel, не могу получить там контекст

            mWeatherAdapter = RepositoryProvider //TODO обращение к репозиторию через viewModel
                    .getInstanceListWeather(context)
                    ?.allItemsWeather?.let { WeatherAdapter(it) }
            mRecyclerView!!.adapter = mWeatherAdapter
        })

        changeView!!.setOnClickListener {
            //TODO планирую реализовать смену отображения на график (х - число, у - значения параметров) и обратно
        }
    }
}