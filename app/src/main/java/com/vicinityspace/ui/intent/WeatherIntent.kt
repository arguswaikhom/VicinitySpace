package com.vicinityspace.ui.intent

sealed class WeatherIntent {
    class FetchWeather(val location: String) : WeatherIntent()
}