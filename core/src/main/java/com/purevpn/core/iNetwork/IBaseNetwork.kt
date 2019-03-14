package com.purevpn.core.iNetwork

import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.Result
import java.io.IOException

interface IBaseNetwork {
    var apiUrl: String
    var apiEndPoint: String
    var apiErrorMessage: String
    val apiSuccessCode: Int
    var apiParams: HashMap<String, String>
    var apiHttpHeaders: HashMap<String, String>
    var apiHttpResponse: String
    var apiMethod: String
    var apiHttpResponseCode: Int


    suspend fun <T> get(url: String, params: HashMap<String, String>,headers:HashMap<String,String>, classOfT: Class<T>): Result<ApiEnvelope<T?>>



    suspend fun <T : Any?> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }

}