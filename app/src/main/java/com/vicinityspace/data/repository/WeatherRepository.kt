package com.vicinityspace.data.repository

import com.vicinityspace.data.api.APIHelperImpl
import com.vicinityspace.data.model.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiHelperImpl: APIHelperImpl) {
    private suspend fun getCurrTemp(location: String) = apiHelperImpl.getCurrTemp(location)
    private suspend fun getForecasts(location: String) = apiHelperImpl.getForecasts(location)

    suspend fun getWeather(location: String) =
        Weather(getCurrTemp(location), getForecasts(location))
}