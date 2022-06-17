package com.vicinityspace.utils

import com.vicinityspace.data.model.Forecast
import com.vicinityspace.data.model.Temperature
import java.text.SimpleDateFormat
import java.util.*

fun Temperature.toCelsius(): Int {
    return (this.temp - 273.15).toInt()
}

fun Date.getWeekDay(): String? {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
}

fun Date.getDayFormat(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)
}

fun List<Forecast>.filteredByUniqueDayAndAverageTemp(): List<Forecast> {
    val uniqueDays = mutableSetOf<String>()
    return filter {
        val day = it.date.getDayFormat()
        if (uniqueDays.contains(day)) {
            false
        } else {
            uniqueDays.add(day)
            true
        }
    }.map {
        val day = it.date.getDayFormat()
        val average =
            filter { f2 -> f2.date.getDayFormat() == day }.map { f3 -> f3.temperature.temp }
                .average()
        it.copy(temperature = Temperature(average))
    }
}
