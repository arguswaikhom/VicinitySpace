package com.vicinityspace.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicinityspace.R
import com.vicinityspace.data.model.Forecast
import com.vicinityspace.databinding.ForecastItemViewBinding
import com.vicinityspace.utils.getWeekDay
import com.vicinityspace.utils.toCelsius

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ForecastItemViewBinding = ForecastItemViewBinding.bind(view)

    companion object {
        fun create(parent: ViewGroup): ForecastViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_item_view, parent, false)
            return ForecastViewHolder(view)
        }
    }

    fun bind(forecast: Forecast) {
        binding.weekDayTv.text = forecast.date.getWeekDay()
        binding.tempTv.text =
            StringBuilder().append(forecast.temperature.toCelsius()).append(" C").toString()
    }
}