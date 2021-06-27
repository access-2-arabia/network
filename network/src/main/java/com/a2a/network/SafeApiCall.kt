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

                val errorMessage =
                    if (Locale.getDefault().language == "en") response?.eDesc
                        ?: "" else response?.aDesc
                        ?: ""
                if (response?.errorCode != 0) {

                    when (response?.errorCode) {
                        16 -> {
                            Resource.Failure(
                                SessionTimeOut(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )


                        }
                        -6 -> {
                            Resource.Failure(
                                PasswordExpired(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }

                        237 -> {
                            Resource.Failure(
                                FirstLogin(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }
                        -5 -> {
                            Resource.Failure(
                                PasswordExpired(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }
                        9 -> {
                            Resource.Failure(
                                InvalidPassword(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )


                        }
                        10 -> {
                            Resource.Failure(
                                InvalidPIN(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }
                        5 -> {
                            Resource.Failure(
                                CannotFindCustomer(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }

                        -2 -> {
                            Resource.Failure(
                                OTPNeeded(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )

                        }
                        5421 -> {
                            Resource.Failure(
                                BiomtricChanged(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )
                        }
                        5110 -> {
                            Resource.Failure(
                                NoHistoryException(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )
                        }
                        5044 -> {
                            Resource.Failure(
                                InvalidPIN(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
                            )
                        }
                        else -> {
                            Resource.Failure(
                                PasswordExpired(Throwable()),
                                errorMessage,
                                request,
                                apiResponse
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
                        request,
                        null
                    )
                    is UnknownHostException -> Resource.Failure(
                        NoInternetException(throwable),
                        "No Internet", request,
                        null
                    )

                    else -> Resource.Failure(throwable, "Something went wrong", request,null)
                }

            }
        }
    }
}