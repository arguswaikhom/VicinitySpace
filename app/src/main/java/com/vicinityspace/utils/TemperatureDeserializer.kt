package com.vicinityspace.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TemperatureDeserializer : JsonDeserializer<Double?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Double {
        return json.asJsonObject["temp"].asDouble
    }
}