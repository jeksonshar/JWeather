package com.jeksonshar.jweather.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jeksonshar.jweather.R
import com.jeksonshar.jweather.ui.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = WeatherFragment()
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}