package com.purevpn.core.helper

import com.purevpn.core.iNetwork.INetworkApi
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response

class WebRequestHelper : KoinComponent {

    private val networkApi: INetworkApi by inject()

    suspend fun get(
        url: String,
        params: HashMap<String, String>,
        header: HashMap<String, String>
    ): Response<ResponseBody>? {
        try {
            return networkApi.callGetRequest(url, params, header).await()
        } catch (e: Exception) {
            return null
        }
    }
}