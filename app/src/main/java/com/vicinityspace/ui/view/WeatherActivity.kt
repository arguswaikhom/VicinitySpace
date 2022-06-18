package com.vicinityspace.ui.view

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.vicinityspace.R
import com.vicinityspace.data.model.Forecast
import com.vicinityspace.ui.adapter.ForecastAdapter
import com.vicinityspace.ui.intent.WeatherIntent
import com.vicinityspace.ui.viewmodel.WeatherViewModel
import com.vicinityspace.ui.viewstate.WeatherState
import com.vicinityspace.utils.filteredByUniqueDayAndAverageTemp
import com.vicinityspace.utils.toCelsius
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.error_screen.view.*
import kotlinx.android.synthetic.main.weather_content_screen.view.*
import kotlinx.coroutines.launch
import kotlin.error

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    private val location: String = "Bangalore"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        observeWeather()
        setFetchWeatherIntent()
        setUpListeners()
    }

    private fun setUpListeners() {
        error.retryBtn.setOnClickListener {
            setFetchWeatherIntent()
        }
    }

    private fun observeWeather() {
        lifecycleScope.launch {
            weatherViewModel.state.collect {
                updateWeatherUI(it)
            }
        }
    }

    private fun updateWeatherUI(state: WeatherState) {
        removeViews()

        when (state) {
            is WeatherState.Fetching -> loading.visibility = View.VISIBLE
            is WeatherState.Error -> error.visibility = View.VISIBLE
            is WeatherState.Content -> {
                content.visibility = View.VISIBLE
                content.forecastRv.visibility = View.GONE

                setTemperature(location, state.weather.temperature.toCelsius())
                setForecasts(state.weather.forecasts)
            }
        }
    }

    private fun setForecasts(forecasts: List<Forecast>) {
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 1000
        transition.addTarget(content.forecastRv)
        TransitionManager.beginDelayedTransition(root, transition)
        content.forecastRv.visibility = View.VISIBLE

        val futureForecasts = forecasts.filteredByUniqueDayAndAverageTemp().drop(1)
        content.forecastRv.adapter = ForecastAdapter(futureForecasts)
    }

    private fun setTemperature(location: String, temperature: Int) {
        val temperatureText = StringBuilder().append(temperature).append("°").toString()
        content.tempTv.text = temperatureText
        content.locationTv.text = location
    }

    private fun setFetchWeatherIntent() {
        lifecycleScope.launch {
            weatherViewModel.fetchWeather.send(WeatherIntent.FetchWeather(location))
        }
    }

    private fun removeViews() {
        loading.visibility = View.GONE
        error.visibility = View.GONE
        content.visibility = View.GONE
    }
}