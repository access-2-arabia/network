package com.a2a.network


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val throwable: Throwable,
        val errorBody: String
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}