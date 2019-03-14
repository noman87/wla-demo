package com.purevpn.network

import com.google.gson.JsonObject
import com.purevpn.core.Common
import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.IBaseNetwork

object NetworkExtension {

    fun <T> get(
        IBaseNetwork: IBaseNetwork,
        response: retrofit2.Response<JsonObject>?,
        classOfT: Class<T>
    ): Result<ApiEnvelope<T?>> {
        val typedResponse = Common.getResponse(response?.body().toString(), classOfT)
        IBaseNetwork.apiHttpResponse = response?.body().toString()
        IBaseNetwork.apiHttpResponseCode = response?.code()!!

        return if (typedResponse.header != null && typedResponse.header?.code == IBaseNetwork.apiSuccessCode) {
            Result.Success(typedResponse)
        } else {
            Result.Error(Exception("Error"))
        }

    }
}