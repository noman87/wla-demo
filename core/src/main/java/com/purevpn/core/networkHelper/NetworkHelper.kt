package com.purevpn.core.networkHelper

import com.google.gson.JsonObject
import com.purevpn.core.Response
import com.purevpn.core.network.NetworkApi

class NetworkHelper(var networkApi: NetworkApi) {

    suspend fun <T> get(
        url: String,params: HashMap<String, String>, header: String): Response<retrofit2.Response<JsonObject>?> {
        return try {
            val apiResponse = networkApi.callGetRequest(url, params, header).await()
            Response.Success(apiResponse)
        } catch (e: Exception) {
            Response.Error(java.lang.Exception("Error"))
        }
    }

}