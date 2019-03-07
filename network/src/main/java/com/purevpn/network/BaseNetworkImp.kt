package com.purevpn.network

import com.purevpn.core.network.BaseNetwork

open class BaseNetworkImp : BaseNetwork {
    override lateinit var apiHttpHeaders: Map<String, String>
    override lateinit var apiHttpResponse: String
    override lateinit var apiMethod: String
    override var apiHttpResponseCode: Int = 0
    override lateinit var apiUrl: String
    override lateinit var apiEndPoint: String
    override lateinit var apiErrorMessage: String
    override  var apiSuccessCode: Int = 0
    override lateinit var apiParams: Map<String, String>


}