package com.vicinityspace.ui.viewstate

import com.vicinityspace.data.model.Weather

sealed class WeatherState {
    object Fetching : WeatherState()
    object Error : WeatherState()
    data class Content(val weather: Weather) : WeatherState()
}