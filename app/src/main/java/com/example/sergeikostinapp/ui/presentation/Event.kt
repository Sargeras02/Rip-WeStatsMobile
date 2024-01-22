package com.example.sergeikostinapp.ui.presentation

sealed class Event {
    data class OnItemClick(val id: Int) : Event()
}