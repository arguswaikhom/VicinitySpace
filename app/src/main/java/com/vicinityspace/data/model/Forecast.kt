package com.vicinityspace.data.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.vicinityspace.utils.ForecastDeserializer
import java.util.*

data class Forecast(
    val date: Date,
    val temperature: Temperature
)

data class ForecastResponse(
    @SerializedName("list")
    @JsonAdapter(ForecastDeserializer::class)
    val forecasts: List<Forecast>
)