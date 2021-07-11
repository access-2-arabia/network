package com.a2a.network


sealed class Resource<out T> {
    data class Success<T>(
        val responseBody: T,
        val successMessage: String,

    ) :
        Resource<T>()

    data class Failure<out REQUEST>(
        val throwable: Throwable,
        val errorMessage: String,
        val request: REQUEST
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}