package com.purevpn.network

import com.google.gson.JsonObject
import com.purevpn.core.Common
import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.BaseNetwork

object NetworkExtension {

    fun <T> get(
        baseNetwork: BaseNetwork,
        response: retrofit2.Response<JsonObject>?,
        classOfT: Class<T>
    ): Result<ApiEnvelope<T?>> {
        val typedResponse = Common.getResponse(response?.body().toString(), classOfT)
        baseNetwork.apiHttpResponse = response?.body().toString()
        baseNetwork.apiHttpResponseCode = response?.code()!!

        return if (typedResponse.header != null && typedResponse.header?.code == baseNetwork.apiSuccessCode) {
            Result.Success(typedResponse)
        } else {
            Result.Error(Exception("Error"))
        }

    }
}