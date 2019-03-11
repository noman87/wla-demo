package com.purevpn.core.network

import com.purevpn.core.Response
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



    suspend fun <T> get(classOfT:Class<T>): Response<T?>

    suspend fun <T> post(classOfT:Class<T>): Response<T?>


    suspend fun <T : Any?> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Response<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Response.Error(IOException(errorMessage, e))
    }

}