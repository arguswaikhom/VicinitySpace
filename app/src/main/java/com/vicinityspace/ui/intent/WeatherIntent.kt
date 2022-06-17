package com.vicinityspace.ui.intent

sealed class WeatherIntent {
    object FetchWeather : WeatherIntent()
}