package com.a2a.network.extenstion

import com.a2a.network.model.Result
import com.google.gson.annotations.SerializedName

open class BaseResponse (
    @SerializedName("A2AResponse")
    var a2AResponse: A2AResponse? = A2AResponse()
) {
    data class A2AResponse(
        @SerializedName("Header")
        var header: Header? = Header()
    ) {
        data class Header(
            @SerializedName("Result")
            var result: Result? = Result()
        )
    }
}