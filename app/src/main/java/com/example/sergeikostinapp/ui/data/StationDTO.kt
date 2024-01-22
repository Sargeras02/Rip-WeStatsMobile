package com.example.sergeikostinapp.ui.data

import com.google.gson.annotations.SerializedName

data class StationDTO(
    @SerializedName("station_id")
    val id: Int,
    val name: String,
    val location: String,
    @SerializedName("opendate")
    val openDate: String,
    val description: String,
    @SerializedName("status")
    val isActive: Boolean,
    @SerializedName("image_url")
    val img: String
)