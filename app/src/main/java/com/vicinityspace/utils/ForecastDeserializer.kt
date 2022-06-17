package com.vicinityspace.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.vicinityspace.data.model.Forecast
import com.vicinityspace.data.model.Temperature
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class ForecastDeserializer : JsonDeserializer<List<Forecast>?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Forecast> {
        val forecastList = mutableListOf<Forecast>()
        json.asJsonArray.forEach {
            forecastList.add(
                Forecast(
                    SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(it.asJsonObject.get("dt_txt").asString)!!,
                    Temperature(it.asJsonObject.get("main").asJsonObject.get("temp").asDouble),
                )
            )
        }
        return forecastList
    }
}