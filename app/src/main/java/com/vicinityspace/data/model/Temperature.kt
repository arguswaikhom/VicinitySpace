package com.vicinityspace.data.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.vicinityspace.utils.TemperatureDeserializer

data class Temperature(
    @SerializedName("main")
    @JsonAdapter(TemperatureDeserializer::class)
    val temp: Double
)
