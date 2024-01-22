package com.example.sergeikostinapp.ui.presentation


data class ScreenState(
    val isLoading: Boolean = true,
    val error: Exception? = null,
    val list: List<Station> = emptyList()
)