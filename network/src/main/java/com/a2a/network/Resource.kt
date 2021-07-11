package com.a2a.network


sealed class Resource<out T> {
    data class Success<out API_RESPONSE, RESPONSE_BODY>(
        val value: API_RESPONSE,
        val successMessage: String,
        val responseBody: RESPONSE_BODY
    ) : Resource<API_RESPONSE>()

    data class Failure<out REQUEST>(
        val throwable: Throwable,
        val errorMessage: String,
        val request: REQUEST
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}