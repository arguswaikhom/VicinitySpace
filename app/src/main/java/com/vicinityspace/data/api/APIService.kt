package com.vicinityspace.data.api

import com.vicinityspace.data.model.ForecastResponse
import com.vicinityspace.data.model.Temperature
import retrofit2.http.GET

const val APP_ID: String = "9b8cb8c7f11c077f8c4e217974d9ee40"
const val LOCATION: String = "Bengaluru"

interface APIService {

    @GET("weather?q=$LOCATION&APPID=$APP_ID")
    suspend fun getCurrTemp(): Temperature

    @GET("forecast?q=$LOCATION&APPID=$APP_ID")
    suspend fun getForecasts(): ForecastResponse
}