package com.a2a.network.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmptyBodyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response.code
        val contentLength = (response.body?.contentLength() ?: -1)
        if (!response.isSuccessful || (code != NO_CONTENT_STATUS_CODE && code != RESET_CONTENT_STATUS_CODE) || contentLength > 0) {
            return response
        }

        val emptyBody = "".toResponseBody("text/plain".toMediaTypeOrNull())
        return response
            .newBuilder()
            .code(OK_STATUS_CODE)
            .body(emptyBody)
            .build()
    }

    private companion object {

        private const val OK_STATUS_CODE = 200
        private const val NO_CONTENT_STATUS_CODE = 204
        private const val RESET_CONTENT_STATUS_CODE = 205
    }
}
