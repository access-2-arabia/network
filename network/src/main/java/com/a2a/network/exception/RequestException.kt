package com.a2a.network.exception

import com.a2a.network.model.OTPResponse
import com.a2a.network.model.Result

open class RequestException(error: Throwable) : RuntimeException(error)
data class NoInternetException(
    val error: Throwable,

    ) : RequestException(error)

data class MapGsonException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class TimeoutException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class ServerUnreachableException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class HttpCallFailureException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class ResponseFailureException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) :
    RequestException(error)

data class NoHistoryException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class NounException(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class SessionTimeOut(
    val error: Throwable,
    val result: Any?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class InvalidPassword(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class PasswordExpired(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class FirstLogin(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class InvalidPIN(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class CannotFindCustomer(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class AlreadyHaveAccount(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class OTPNeeded(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class BiomtricChanged(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error)

data class TokenExpire(
    val error: Throwable,
    val result: Result?,
    val response: OTPResponse.A2AResponse
) : RequestException(error = error)
