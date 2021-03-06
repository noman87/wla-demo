package com.purevpn.network

import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.ApiException
import com.purevpn.core.helper.Utilities
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import com.purevpn.core.models.Result
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response
import java.lang.reflect.ParameterizedType
import java.net.URI


open class BaseNetworkImpl : IBaseNetwork, KoinComponent {
    private val webRequestHelper: WebRequestHelper by inject()


    override suspend fun <T> get(
        url: String,
        params: HashMap<String, String>,
        headers: HashMap<String, String>,
        type: T,
        parameterizedType: ParameterizedType
    ): Result<T> {
        setProperties(url, params, headers)
        val httpResponse = webRequestHelper.get(apiUrl, apiParams, apiHttpHeaders)
        when (httpResponse) {
            is Result.Success -> {
                httpResponse.data.apply {
                    setHttpApiProperties(this)
                    sendApiEvent(this)
                    when (code()) {
                        200 -> {
                            //Http status code is 200
                            val response: String? = body()?.string()
                            if (response != null) {
                                response.let {
                                    val mapObject = Utilities.mapModel(type, it, parameterizedType)
                                    return when (mapObject) {
                                        is Result.Success -> {
                                            Result.Success(mapObject.data)

                                        }
                                        is Result.Error -> {
                                            val exception = ApiException(
                                                Errors.ApiErrorCodes._1001,
                                                mapObject.exception
                                            )
                                            Result.Error(exception)
                                        }
                                    }
                                }
                            } else {
                                val exception = ApiException(
                                    Errors.ApiErrorCodes._1001, null
                                )
                                Result.Error(exception)
                            }


                        }
                        else -> {
                            //Http status code other than 200
                            val exception = ApiException(
                                Errors.ApiErrorCodes._1004, null
                            )
                            Result.Error(exception)

                        }
                    }

                }

            }
            is Result.Error -> {
                return Result.Error(httpResponse.exception)
            }

        }

        val exception =
            ApiException(Errors.ApiErrorCodes._1001, null)
        return Result.Error(exception)
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