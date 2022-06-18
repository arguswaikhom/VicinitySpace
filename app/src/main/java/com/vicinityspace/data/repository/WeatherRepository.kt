package com.vicinityspace.data.repository

import com.vicinityspace.data.api.APIHelper
import com.vicinityspace.data.model.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiHelper: APIHelper) {
    private suspend fun getCurrTemp(location: String) = apiHelper.getCurrTemp(location)
    private suspend fun getForecasts(location: String) = apiHelper.getForecasts(location)

    suspend fun getWeather(location: String) =
        Weather(getCurrTemp(location), getForecasts(location))
}