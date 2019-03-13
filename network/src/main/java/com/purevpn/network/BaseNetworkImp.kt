package com.purevpn.network

import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.networkHelper.NetworkHelper

open class BaseNetworkImp(private val networkHelper: NetworkHelper) : BaseNetwork {

    override suspend fun <T> get(classOfT: Class<T>): Result<ApiEnvelope<T?>> {
        val httpResponse = networkHelper.get<Any>(apiUrl, apiParams, apiAccessToken)
        return try {
            when (httpResponse) {
                is Result.Success -> {
                    NetworkExtension.get(this, httpResponse.data, classOfT)
                }
                is Result.Error -> {
                    Result.Error(Exception("Error"))
                }
            }
        } catch (e: Exception) {
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