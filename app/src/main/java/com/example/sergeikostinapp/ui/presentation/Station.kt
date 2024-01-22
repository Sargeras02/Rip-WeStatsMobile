package com.example.sergeikostinapp.ui.presentation

import com.example.sergeikostinapp.ui.data.StationDTO

data class Station(
    val id: Int,
    val img: String,
    val name: String,
    val location: String,
    val openDate: String,
    val description: String,
    val isActive: Boolean,
    val isExpanded: Boolean
)

fun mapToModel(entity: StationDTO) = Station(
    id = entity.id,
    name = entity.name,
    img = entity.img,
    location = "Location: ${entity.location}",
    openDate = "Open date: ${entity.openDate}",
    description = "Description: ${entity.description}",
    isActive = entity.isActive,
    isExpanded = false
)