package com.purevpn.core.networkHelper

import com.google.gson.JsonObject
import com.purevpn.core.Result
import com.purevpn.core.network.NetworkApi
import retrofit2.Response

class NetworkHelper(var networkApi: NetworkApi) {

    suspend fun <T> get(
        url: String,params: HashMap<String, String>, header: String): Result<Response<JsonObject>?> {
        return try {
            val apiResponse = networkApi.callGetRequest(url, params, header).await()
            Result.Success(apiResponse)
        } catch (e: Exception) {
            Result.Error(java.lang.Exception("Error"))
        }
    }

}