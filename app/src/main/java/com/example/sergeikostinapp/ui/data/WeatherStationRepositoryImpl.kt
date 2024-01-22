package com.example.sergeikostinapp.ui.data

import com.example.sergeikostinapp.ui.domain.WeatherStationRepository
import com.example.sergeikostinapp.ui.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherStationRepositoryImpl : WeatherStationRepository {

    override fun getWeatherStationList(): Flow<Resource<List<StationDTO>>> = flow {
        emit(Resource.loading())
        delay(1000L)
        val res = try {
            val response = NetworkApi.api.getStations()
            Resource.success(response)
        } catch (e: Exception) {
            Resource.error(e)
        }
        emit(res)
    }
}