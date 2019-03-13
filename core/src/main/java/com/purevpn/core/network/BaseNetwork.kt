package com.purevpn.core.network

import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import java.io.IOException

interface BaseNetwork {
    var apiUrl: String
    var apiEndPoint: String
    var apiErrorMessage: String
    var apiSuccessCode: Int
    var apiParams: HashMap<String, String>
    var apiHttpHeaders: HashMap<String, String>
    var apiHttpResponse: String
    var apiMethod: String
    var apiHttpResponseCode: Int
    var apiAccessToken: String



    suspend fun <T> get(classOfT:Class<T>): Result<ApiEnvelope<T?>>



    suspend fun <T : Any?> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }

}