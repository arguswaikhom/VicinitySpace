package com.vicinityspace

import android.app.Application
import com.vicinityspace.data.api.APIHelperImpl
import com.vicinityspace.data.api.RetrofitBuilder
import com.vicinityspace.data.repository.WeatherRepository

class WeatherApplication : Application() {
    private val apiHelperImpl by lazy { APIHelperImpl(RetrofitBuilder.apiService) }
    val weatherRepository by lazy { WeatherRepository(apiHelperImpl) }
}