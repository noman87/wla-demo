package com.purevpn.core.iNetwork

import com.purevpn.core.models.Result
import java.lang.reflect.ParameterizedType

interface IBaseNetwork {
    var apiUrl: String
    val apiEndPoint: String
    var apiErrorMessage: String
    val apiSuccessCode: Int
    var apiParams: HashMap<String, String>
    var apiHttpHeaders: HashMap<String, String>
    var apiHttpResponse: String
    var apiMethod: String
    var apiHttpResponseCode: Int


    suspend fun <T> get(
        url: String,
        params: HashMap<String, String>,
        headers: HashMap<String, String>,
        type: T,
        parameterizedType: ParameterizedType
    ): Result<T>


    /*suspend fun <T : Any?> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Result.Error(java.lang.Exception())
    }*/

}