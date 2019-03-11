package com.purevpn.network

import com.purevpn.core.Response
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.networkHelper.NetworkHelper

open class BaseNetworkImp(val networkHelper: NetworkHelper) : BaseNetwork {

    override suspend fun <T> get(classOfT: Class<T>): Response<T?> {
        return networkHelper.get(this, classOfT)
    }

    override suspend fun <T> post(classOfT: Class<T>): Response<T?> {
        return networkHelper.post(this, classOfT)
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