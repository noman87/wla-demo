package com.purevpn.network

import com.purevpn.core.Common
import com.purevpn.core.Response
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.networkHelper.NetworkHelper

open class BaseNetworkImp(private val networkHelper: NetworkHelper) : BaseNetwork {

    override suspend fun <T> get(classOfT: Class<T>): Response<ApiEnvelope<T?>> {
        val httpResponse = networkHelper.get<Any>(apiUrl, apiParams, apiAccessToken)
        return try {
            when (httpResponse) {
                is Response.Success -> {
                    apiHttpResponse = httpResponse.data?.body().toString()
                    apiHttpResponseCode = httpResponse.data?.code()!!
                    val typedResponse = Common.getResponse(httpResponse.data?.body().toString(), classOfT)
                    if (typedResponse.header?.code == apiSuccessCode) {
                        Response.Success(typedResponse)
                    } else {
                        Response.Error(Exception("Error"))
                    }

                }
                is Response.Error -> {
                    Response.Error(Exception("Error"))
                }
            }
        } catch (e: Exception) {
            Response.Error(Exception("Error"))
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