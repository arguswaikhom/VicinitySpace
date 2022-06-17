package com.vicinityspace.data.repository

import com.vicinityspace.data.api.APIHelperImpl
import com.vicinityspace.data.model.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val apiHelperImpl: APIHelperImpl) {
    private suspend fun getCurrTemp() = apiHelperImpl.getCurrTemp()
    private suspend fun getForecasts() = apiHelperImpl.getForecasts()

    suspend fun getWeather() = Weather(getCurrTemp(), getForecasts())
}