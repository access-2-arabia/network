package com.a2a.network

import com.a2a.network.exception.*
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
                        16 -> {
                            Resource.Failure(
                                SessionTimeOut(
                                    Throwable(),
                                    response, body
                                ),
                                errorMessage,
                                request
                            )


                        }
                        -6 -> {
                            Resource.Failure(
                                PasswordExpired(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )

                        }

                        237 -> {
                            Resource.Failure(
                                FirstLogin(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )

                        }
                        -5 -> {
                            Resource.Failure(
                                PasswordExpired(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )

                        }
                        9 -> {
                            Resource.Failure(
                                InvalidPassword(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )


                        }
                        10 -> {
                            Resource.Failure(
                                InvalidPIN(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )

                        }
                        5 -> {
                            Resource.Failure(
                                CannotFindCustomer(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )

                        }

                        -2 -> {
                            Resource.Failure(
                                OTPNeeded(
                                    Throwable(),
                                    response,
                                    body
                                ),
                                errorMessage,
                                request
                            )

                        }
                        5421 -> {
                            Resource.Failure(
                                BiomtricChanged(
                                    Throwable(),
                                    response,
                                    body
                                ),
                                errorMessage,
                                request
                            )
                        }
                        5110 -> {
                            Resource.Failure(
                                NoHistoryException(
                                    Throwable(),
                                    response,
                                    body,
                                ),
                                errorMessage,
                                request
                            )
                        }
                        5044 -> {
                            Resource.Failure(
                                InvalidPIN(
                                    Throwable(),
                                    response,
                                    body
                                ),
                                errorMessage,
                                request
                            )
                        }
                        else -> {
                            Resource.Failure(
                                PasswordExpired(Throwable(), response, body),
                                errorMessage,
                                request
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
                        "No Internet",
                        request
                    )
                    is UnknownHostException -> Resource.Failure(
                        NoInternetException(throwable),
                        "No Internet", request
                    )

                    else -> Resource.Failure(throwable, "Something went wrong", request)
                }

            }
        }
    }
}