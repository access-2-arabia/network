package com.a2a.network

import com.a2a.network.exception.NoInternetException
import com.a2a.network.exception.PasswordExpired
import com.a2a.network.model.BaseResponse
import com.a2a.network.model.OTPResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.*


interface SafeApiCall {
    suspend fun <REQUEST, API_CALL> safeApiCall(
        request: REQUEST,
        apiCall: suspend () -> API_CALL
    ): Resource<API_CALL> {
        return withContext(Dispatchers.IO) {
            try {


                // for Encryption
//            val encryptDataHelper = EncryptionDecryptionHelper()
//            val result = encryptDataHelper.decryptData(
//                apiCall.invoke().toString(),
//                "5-SyNB6QAErWbPumM5QCjFW7dV2RqxH3"
//            )
//            val response = (Gson().fromJson(
//                result,
//                BaseResponse::class.java
//            ))?.a2AResponse?.header?.result

                val apiResponse = apiCall.invoke()
                val response = Gson().fromJson(
                    Gson().toJson(apiResponse),
                    BaseResponse::class.java
                ).a2AResponse?.header?.result


                val body = Gson().fromJson(
                    Gson().toJson(apiResponse),
                    OTPResponse::class.java
                ).a2AResponse


                val errorMessage =
                    if (Locale.getDefault().language == "en") response?.eDesc
                        ?: "" else response?.aDesc
                        ?: ""
                if (response?.errorCode != 0) {

                    when (response?.errorCode) {
                        -5 -> {

                            Resource.Failure(
                                PasswordExpired(Throwable(), response, body),
                                errorMessage
                            )
                        }
                        -6 -> {

                            Resource.Failure(
                                PasswordExpired(Throwable(), response, body),
                                errorMessage
                            )
                        }
                        else -> {
                            Resource.Failure(
                                PasswordExpired(Throwable(), response, body),
                                errorMessage
                            )
                        }
                    }
                } else {
                    Resource.Success(apiResponse)
                }

            } catch (throwable: Throwable) {
                when (throwable) {

                    is ConnectException -> Resource.Failure(
                        NoInternetException(throwable),
                        "No Internet"
                    )
                    is UnknownHostException -> Resource.Failure(
                        NoInternetException(throwable),
                        "No Internet"
                    )

                    else -> Resource.Failure(throwable, "Someting went wrong")
                }

            }
        }
    }
}