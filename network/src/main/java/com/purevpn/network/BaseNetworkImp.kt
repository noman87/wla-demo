package com.purevpn.network

import com.google.gson.JsonObject
import com.purevpn.core.Common
import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.networkHelper.NetworkHelper
import okhttp3.ResponseBody
import retrofit2.Response

open class BaseNetworkImp(private val networkHelper: NetworkHelper) : BaseNetwork {

    override suspend fun <T> get(classOfT: Class<T>): Result<ApiEnvelope<T?>> {
        val httpResponse = networkHelper.get<Any>(apiUrl, apiParams, apiAccessToken)
        try {
            when (httpResponse) {
                is Result.Success -> {
                    httpResponse.data.apply {
                        //setApiProperties(this)
                        return getTypedResponse(this, classOfT)
                    }
                }
                is Result.Error -> return Result.Error(Exception("Error"))
            }
        } catch (e: Exception) {
            return Result.Error(Exception("Error"))
        }
    }

    private fun setApiProperties(httpResponse: Response<JsonObject>?) {
        httpResponse?.apply {
            apiHttpResponse = body()?.toString().orEmpty()
            apiHttpResponseCode = code()
            apiErrorMessage = errorBody()?.string().orEmpty()
            sendEvent()
        }
    }

    private fun sendEvent() {

    }

    private fun <T> getTypedResponse(response: Response<ResponseBody>, classOfT: Class<T>): Result<ApiEnvelope<T?>> {
        val typedResponse = Common.getResponse(response.body()?.string().orEmpty(), classOfT)
        return if (typedResponse.header != null && typedResponse.header?.code == apiSuccessCode) {
            Result.Success(typedResponse)
        } else {
            Result.Error(Exception("Error"))
        }

    }


    override lateinit var apiAccessToken: String
    override lateinit var apiHttpHeaders: HashMap<String, String>
    override lateinit var apiHttpResponse: String
    override lateinit var apiMethod: String
    override var apiHttpResponseCode: Int = 0
    override lateinit var apiUrl: String
    override lateinit var apiEndPoint: String
    override lateinit var apiErrorMessage: String
    override var apiSuccessCode: Int = 0
    override lateinit var apiParams: HashMap<String, String>


}