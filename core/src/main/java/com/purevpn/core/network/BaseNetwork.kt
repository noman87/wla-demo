package com.purevpn.core.network

import com.purevpn.core.Response
import java.io.IOException

interface BaseNetwork {
    var apiUrl: String
    var apiEndPoint: String
    var apiErrorMessage: String
    var apiSuccessCode: String
    var apiParams: Map<String, String>


    suspend fun <T : Any?> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Response<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Response.Error(IOException(errorMessage, e))
    }

}