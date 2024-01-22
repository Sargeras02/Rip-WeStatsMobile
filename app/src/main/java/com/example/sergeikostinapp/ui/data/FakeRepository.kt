package com.example.sergeikostinapp.ui.data

import com.example.sergeikostinapp.ui.domain.WeatherStationRepository
import com.example.sergeikostinapp.ui.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

// todo remove this file


private val stations = listOf(
    StationDTO(
        id = 1,
        name = "Station 1",
        location = "Location 1",
        openDate = "2023-01-01",
        description = "Description 1",
        isActive = true,
        img = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Wetterstation01.jpeg/330px-Wetterstation01.jpeg"
    ),
    StationDTO(
        id = 2,
        name = "Station 2",
        location = "Location 2",
        openDate = "2023-02-01",
        description = "Description 2",
        isActive = false,
        img = "https://example.com/image2.jpg"
    ),
)

class FakeRepository : WeatherStationRepository {
    override fun getWeatherStationList(): Flow<Resource<List<StationDTO>>> = flow {
        emit(Resource.loading())
        delay(1000L)
        val res = Resource.success(stations)
//        val res = Resource.error(ex = Exception(), data = null)
        emit(res)
    }
}