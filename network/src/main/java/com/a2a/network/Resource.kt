package com.a2a.network


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure<out REQUEST,RESPONSE>(
        val throwable: Throwable,
        val errorMessage: String,
        val request:REQUEST,
        val response:RESPONSE
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}