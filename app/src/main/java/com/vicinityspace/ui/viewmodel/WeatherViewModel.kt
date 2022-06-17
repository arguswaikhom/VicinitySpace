package com.vicinityspace.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vicinityspace.data.repository.WeatherRepository
import com.vicinityspace.ui.intent.WeatherIntent
import com.vicinityspace.ui.viewstate.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    val fetchWeather = Channel<WeatherIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<WeatherState>(WeatherState.Fetching)

    val state: StateFlow<WeatherState>
        get() = _state

    init {
        viewModelScope.launch {
            fetchWeather.consumeAsFlow().collect {
                when (it) {
                    is WeatherIntent.FetchWeather -> fetchWeather()
                }
            }
        }
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            _state.value = WeatherState.Fetching
            _state.value = try {
                WeatherState.Content(repository.getWeather())
            } catch (e: Exception) {
                WeatherState.Error
            }
        }
    }
}

class WeatherVMFactory(
    private val weatherRepository: WeatherRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
