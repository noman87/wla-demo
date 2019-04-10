package com.purevpn.core.helper

import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.AppException
import com.purevpn.core.iNetwork.INetworkApi
import com.purevpn.core.models.Result
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

    ): Result<Response<ResponseBody>> {
        return try {
            val await = networkApi.callGetRequest(url, params, header).await()
            Result.Success(await)
        } catch (ex: Exception) {
            val exception =
                AppException.makeApiException(Errors._1003, Errors.getErrorMessage(Errors._1003), ex)
            Result.Error(exception)
        }
    }

}