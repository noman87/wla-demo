package com.purevpn.core.network

interface BaseNetwork {
    var apiUrl: String
    var apiEndPoint: String
    var apiErrorMessage: String
    var apiSuccessCode: String
    var apiParams: Map<String,String>
}