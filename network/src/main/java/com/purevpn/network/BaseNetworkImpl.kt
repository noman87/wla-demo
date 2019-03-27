package com.purevpn.network

import com.purevpn.core.helper.Utilities
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response
import java.lang.reflect.ParameterizedType
import java.net.URI


open class BaseNetworkImpl : IBaseNetwork, KoinComponent {
    private val webRequestHelper: WebRequestHelper by inject()
    override suspend fun <T> get(url: String, params: HashMap<String, String>, headers: HashMap<String, String>, type: T, parameterizedType: ParameterizedType): T? {
        setProperties(url, params, headers)
        val httpResponse: Response<ResponseBody>? = webRequestHelper.get(apiUrl, apiParams, apiHttpHeaders)
        httpResponse?.run {
            setHttpApiProperties(this)
            sendApiEvent(this)
            return body()?.string()?.let {
                Utilities.mapModel(type, it, parameterizedType)

            }
        }
        return null
    }

    private fun sendApiEvent(response: Response<ResponseBody>) {


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