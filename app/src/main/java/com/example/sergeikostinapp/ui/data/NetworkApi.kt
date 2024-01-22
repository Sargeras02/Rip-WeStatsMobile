package com.example.sergeikostinapp.ui.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


interface NetworkApi {
    @GET("/stations")
    suspend fun getStations(): List<StationDTO>

    companion object {
        private const val BASE_URL = "http://192.168.0.100:8000"

        private fun createRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api: NetworkApi by lazy { createRetrofit().create() }
    }
}