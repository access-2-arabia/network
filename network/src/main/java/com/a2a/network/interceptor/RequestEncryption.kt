package com.a2a.network.interceptor

import com.a2a.network.securityHelper.EncryptionDecryptionHelper
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import okio.IOException


class RequestEncryption : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val oldBody = request.body
        val encryptDataHelper = EncryptionDecryptionHelper()
        try {
            val buffer = Buffer()
            oldBody!!.writeTo(buffer)
            val newSt =
                encryptDataHelper.encryptData(buffer.readUtf8(), "5-SyNB6QAErWbPumM5QCjFW7dV2RqxH3")

            val mediaType = "application/json".toMediaTypeOrNull()
            val addCaution = "\"" + newSt + "\""
            val body = RequestBody.create(mediaType, addCaution)
            request =
                request.newBuilder().header("Authorization", "5-SyNB6QAErWbPumM5QCjFW7dV2RqxH3")
                    .method(request.method, body).build()
        } catch (e: Exception) {

        }
        return chain.proceed(request)
    }
}