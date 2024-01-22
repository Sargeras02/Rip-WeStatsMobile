package com.example.sergeikostinapp.ui.domain

import com.example.sergeikostinapp.ui.data.StationDTO
import com.example.sergeikostinapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherStationRepository {
    fun getWeatherStationList() : Flow<Resource<List<StationDTO>>>
}