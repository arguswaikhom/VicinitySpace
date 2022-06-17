package com.vicinityspace.data.model

class Weather(temperature: Temperature, forecasts: List<Forecast>) {
    val temperature: Temperature
    val forecasts: List<Forecast>

    init {
        this.temperature = temperature
        this.forecasts = forecasts
    }
}