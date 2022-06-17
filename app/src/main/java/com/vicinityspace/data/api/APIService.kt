package com.vicinityspace.data.api

import com.vicinityspace.data.model.ForecastResponse
import com.vicinityspace.data.model.Temperature
import retrofit2.http.GET
import retrofit2.http.Query

const val APP_ID: String = "9b8cb8c7f11c077f8c4e217974d9ee40"

interface APIService {

    @GET("weather")
    suspend fun getCurrTemp(
        @Query("q") location: String,
        @Query("APPID") appId: String = APP_ID
    ): Temperature

    @GET("forecast")
    suspend fun getForecasts(
        @Query("q") location: String,
        @Query("APPID") appId: String = APP_ID
    ): ForecastResponse
}