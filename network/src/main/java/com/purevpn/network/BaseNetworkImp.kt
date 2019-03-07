package com.purevpn.network

import com.purevpn.core.network.BaseNetwork

open class BaseNetworkImp : BaseNetwork {
    override lateinit var apiUrl: String
    override lateinit var apiEndPoint: String
    override lateinit var apiErrorMessage: String
    override lateinit var apiSuccessCode: String
    override lateinit var apiParams: Map<String, String>






}