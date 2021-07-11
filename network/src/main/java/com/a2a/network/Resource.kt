package com.a2a.network


sealed class Resource<out T> {
    data class Success<out T>(
        val value: T,
        val successMessage: String,
        val responseBody: Any
    ) :
        Resource<T>()

    data class Failure<out REQUEST>(
        val throwable: Throwable,
        val errorMessage: String,
        val request: REQUEST
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}