package com.vicinityspace.data.api

import com.vicinityspace.data.model.Forecast
import com.vicinityspace.data.model.Temperature
import javax.inject.Inject

class APIHelperImpl @Inject constructor(private val apiService: APIService) : APIHelper {

    override suspend fun getCurrTemp(location: String): Temperature {
        return apiService.getCurrTemp(location)
    }

    override suspend fun getForecasts(location: String): List<Forecast> {
        return apiService.getForecasts(location).forecasts
    }
}