package com.vicinityspace.data.api

import com.vicinityspace.data.model.Forecast
import com.vicinityspace.data.model.Temperature

class APIHelperImpl(private val apiService: APIService) : APIHelper {

    override suspend fun getCurrTemp(): Temperature {
        return apiService.getCurrTemp()
    }

    override suspend fun getForecasts(): List<Forecast> {
        return apiService.getForecasts().forecasts
    }
}