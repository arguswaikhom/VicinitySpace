package com.vicinityspace.data.repository

import com.vicinityspace.data.api.APIHelperImpl
import com.vicinityspace.data.model.Weather

class WeatherRepository(private val apiHelperImpl: APIHelperImpl) {
    private suspend fun getCurrTemp() = apiHelperImpl.getCurrTemp()
    private suspend fun getForecasts() = apiHelperImpl.getForecasts()

    suspend fun getWeather() = Weather(getCurrTemp(), getForecasts())
}