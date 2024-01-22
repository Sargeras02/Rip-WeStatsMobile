package com.example.sergeikostinapp.ui.utils

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val exception: Exception?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(ex: Exception, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                ex
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}