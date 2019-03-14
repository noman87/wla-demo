package com.purevpn.network

import com.purevpn.core.Common
import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.IBaseNetwork
import com.purevpn.core.networkHelper.WebRequestHelper
import okhttp3.ResponseBody
import retrofit2.Response

open class IBaseNetworkImp(private val webRequestHelper: WebRequestHelper) : IBaseNetwork {

    override suspend fun <T> get(url: String, params: HashMap<String, String>, headers: HashMap<String, String>, classOfT: Class<T>): Result<ApiEnvelope<T?>> {
        setProperties(url, params, headers)
        val httpResponse = webRequestHelper.get<Any>(apiUrl, apiParams, apiHttpHeaders)
        try {
            when (httpResponse) {
                is Result.Success -> {
                    httpResponse.data.apply {
                        setHttpApiProperties(this)
                        return getTypedResponse(this, classOfT)
                    }
                }
                is Result.Error -> return Result.Error(Exception("Error"))
            }
        } catch (e: Exception) {
            return Result.Error(Exception("Error"))
        }
    }

    private fun setHttpApiProperties(httpResponse: Response<ResponseBody>?) {
        httpResponse?.apply {
            apiHttpResponse = body()?.toString().orEmpty()
            apiHttpResponseCode = code()
            apiErrorMessage = errorBody()?.string().orEmpty()
            trackApi(httpResponse.raw())
        }
    }

    private fun trackApi(rawResponse: okhttp3.Response) {

    }


    private fun setProperties(url: String, params: HashMap<String, String>, headers: HashMap<String, String>) {
        apiUrl = url
        apiParams = params
        apiHttpHeaders = headers

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