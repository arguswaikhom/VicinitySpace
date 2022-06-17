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
import com.vicinityspace.data.model.Forecast
import com.vicinityspace.databinding.ActivityWeatherBinding
import com.vicinityspace.ui.adapter.ForecastAdapter
import com.vicinityspace.ui.intent.WeatherIntent
import com.vicinityspace.ui.viewmodel.WeatherViewModel
import com.vicinityspace.ui.viewstate.WeatherState
import com.vicinityspace.utils.filteredByUniqueDayAndAverageTemp
import com.vicinityspace.utils.toCelsius
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private val binding: ActivityWeatherBinding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeWeather()
        setFetchWeatherIntent()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.error.retryBtn.setOnClickListener {
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
            is WeatherState.Fetching -> binding.loading.root.visibility = View.VISIBLE
            is WeatherState.Error -> binding.error.root.visibility = View.VISIBLE
            is WeatherState.Content -> {
                binding.content.root.visibility = View.VISIBLE
                binding.content.forecastRv.visibility = View.GONE

                setTemperature("Bangalore", state.weather.temperature.toCelsius())
                setForecasts(state.weather.forecasts)
            }
        }
    }

    private fun setForecasts(forecasts: List<Forecast>) {
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 1000
        transition.addTarget(binding.content.forecastRv)
        TransitionManager.beginDelayedTransition(binding.root, transition)
        binding.content.forecastRv.visibility = View.VISIBLE

        binding.content.forecastRv.adapter =
            ForecastAdapter(forecasts.filteredByUniqueDayAndAverageTemp())
    }

    private fun setTemperature(location: String, temperature: Int) {
        val temperatureText = StringBuilder().append(temperature).append("°").toString()
        binding.content.tempTv.text = temperatureText
        binding.content.locationTv.text = location
    }

    private fun setFetchWeatherIntent() {
        lifecycleScope.launch {
            weatherViewModel.fetchWeather.send(WeatherIntent.FetchWeather)
        }
    }

    private fun removeViews() {
        binding.loading.root.visibility = View.GONE
        binding.error.root.visibility = View.GONE
        binding.content.root.visibility = View.GONE
    }
}