package com.vicinityspace.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicinityspace.data.model.Forecast

class ForecastAdapter(private val forecasts: List<Forecast>) :
    RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }

    override fun getItemCount(): Int {
        return if (forecasts.size > 4) 4 else forecasts.size;
    }
}