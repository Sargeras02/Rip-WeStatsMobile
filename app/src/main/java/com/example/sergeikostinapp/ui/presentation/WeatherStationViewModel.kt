package com.example.sergeikostinapp.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sergeikostinapp.ui.data.FakeRepository
import com.example.sergeikostinapp.ui.data.StationDTO
import com.example.sergeikostinapp.ui.data.WeatherStationRepositoryImpl
import com.example.sergeikostinapp.ui.domain.WeatherStationRepository
import com.example.sergeikostinapp.ui.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// change to WeatherStationRepositoryImpl
class WeatherStationViewModel(private val repo: WeatherStationRepository = FakeRepository()) :
    ViewModel() {

    val screenState: StateFlow<ScreenState> get() = _screenState
    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState())

    init {
        fetchStationList()
    }

    fun onEvent(event: Event) = when (event) {
        is Event.OnItemClick -> onItemClick(event.id)
    }

    private fun fetchStationList() {
        viewModelScope.launch {
            repo.getWeatherStationList().collect { processStationList(it) }
        }
    }

    private fun processStationList(res: Resource<List<StationDTO>>) {
        _screenState.update {
            it.copy(
                isLoading = res.status == Resource.Status.LOADING,
                error = res.exception?.takeIf { res.status == Resource.Status.ERROR },
                list = res.data?.map { st -> mapToModel(st) }
                    .takeIf { res.status == Resource.Status.SUCCESS } ?: emptyList()
            )
        }
    }

    private fun onItemClick(id: Int) {
        _screenState.update { state ->
            val newItems = state.list.map {
                if (it.id == id) it.copy(isExpanded = !it.isExpanded) else it
            }
            state.copy(list = newItems)
        }
    }


}