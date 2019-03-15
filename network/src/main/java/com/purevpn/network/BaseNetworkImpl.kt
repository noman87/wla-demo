package com.purevpn.network

import com.purevpn.core.helper.Utilities
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import com.purevpn.core.models.Result
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response
import java.lang.reflect.Type
import java.net.URI

open class BaseNetworkImpl : IBaseNetwork, KoinComponent {
    private val webRequestHelper: WebRequestHelper by inject()
    override suspend fun get(url: String, params: HashMap<String, String>, headers: HashMap<String, String>, type: Type): Any? {
        setProperties(url, params, headers)
        val httpResponse = webRequestHelper.get(apiUrl, apiParams, apiHttpHeaders)
        try {
            when (httpResponse) {
                is Result.Success -> {
                    httpResponse.data.apply {
                        setHttpApiProperties(this)
                        trackApi(this)
                        return Utilities.getResponse<Any>(body()?.string().orEmpty(), type)
                    }
                }
                is Result.Error -> return null
            }
        } catch (e: Exception) {
            return null
        }
    }

    private fun trackApi(response: Response<ResponseBody>) {

    }

    private fun setHttpApiProperties(httpResponse: Response<ResponseBody>?) {
        httpResponse?.apply {
            apiHttpResponse = body()?.toString().orEmpty()
            apiHttpResponseCode = code()
            apiErrorMessage = errorBody()?.string().orEmpty()
        }
    }


    private fun setProperties(url: String, params: HashMap<String, String>, headers: HashMap<String, String>) {
        apiUrl = url
        apiParams = params
        apiHttpHeaders = headers

    }

    override lateinit var apiHttpHeaders: HashMap<String, String>
    override lateinit var apiHttpResponse: String
    override lateinit var apiMethod: String
    override var apiHttpResponseCode: Int = 0
    override lateinit var apiUrl: String
    override val apiEndPoint: String get() = URI(this.apiUrl).path
    override lateinit var apiErrorMessage: String
    override val apiSuccessCode: Int = 0
    override lateinit var apiParams: HashMap<String, String>


}