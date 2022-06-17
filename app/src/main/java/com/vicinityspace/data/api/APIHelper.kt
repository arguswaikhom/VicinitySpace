package com.vicinityspace.data.api

import com.vicinityspace.data.model.Forecast
import com.vicinityspace.data.model.Temperature

interface APIHelper {
    suspend fun getCurrTemp(location: String): Temperature

    suspend fun getForecasts(location: String): List<Forecast>
}