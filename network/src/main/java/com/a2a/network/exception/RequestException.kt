package com.a2a.network.exception


open class RequestException(error: Throwable) : RuntimeException(error)
data class NoInternetException(
    val error: Throwable,

    ) : RequestException(error)

data class MapGsonException(
    val error: Throwable,

    ) : RequestException(error)

data class TimeoutException(
    val error: Throwable,

    ) : RequestException(error)

data class ServerUnreachableException(
    val error: Throwable,

    ) : RequestException(error)

data class HttpCallFailureException(
    val error: Throwable,

    ) : RequestException(error)

data class ResponseFailureException(
    val error: Throwable,

    ) :
    RequestException(error)

data class NoHistoryException(
    val error: Throwable,

    ) : RequestException(error)

data class NounException(
    val error: Throwable,

    ) : RequestException(error)

data class SessionTimeOut(
    val error: Throwable,

    ) : RequestException(error)

data class InvalidPassword(
    val error: Throwable,

    ) : RequestException(error)

data class PasswordExpired(
    val error: Throwable,

    ) : RequestException(error)

data class FirstLogin(
    val error: Throwable,

    ) : RequestException(error)

data class InvalidPIN(
    val error: Throwable,

    ) : RequestException(error)

data class CannotFindCustomer(
    val error: Throwable,

    ) : RequestException(error)

data class AlreadyHaveAccount(
    val error: Throwable,

    ) : RequestException(error)

data class OTPNeeded(
    val error: Throwable,

    ) : RequestException(error)

data class BiomtricChanged(
    val error: Throwable,

    ) : RequestException(error)

data class TokenExpire(
    val error: Throwable,

    ) : RequestException(error = error)
