package com.purevpn.core.networkHelper

import com.purevpn.core.Result
import com.purevpn.core.network.INetworkApi
import okhttp3.ResponseBody
import retrofit2.Response

class WebRequestHelper(var INetworkApi: INetworkApi) {

    suspend fun <T> get(
        url: String,params: HashMap<String, String>, header: HashMap<String,String>): Result<Response<ResponseBody>> {
        return try {
            val apiResponse = INetworkApi.callGetRequest(url, params, header).await()
            Result.Success(apiResponse)
        } catch (e: Exception) {
            Result.Error(java.lang.Exception("Error"))
        }
    }

}