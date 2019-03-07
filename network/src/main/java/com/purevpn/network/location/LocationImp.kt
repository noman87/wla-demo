package com.purevpn.network.location

import com.purevpn.core.Response
import com.purevpn.core.network.Location
import com.purevpn.core.network.NetworkApi
import com.purevpn.network.BaseNetworkImp

class LocationImp(private val api: NetworkApi) : BaseNetworkImp(), Location {
    override suspend fun getPublicApi() =
        safeApiCall(
            call = {
                apiParams = HashMap()
                (apiParams as HashMap<String, String>)["method"] = "json"
                apiUrl = "ipmanagement/v1/ip2Location"

                val response = api.getIp(apiUrl, apiParams).await()
                apiHttpResponseCode = response.code()
                apiErrorMessage = response.errorBody().toString()

                return@safeApiCall if (response.body()?.header?.code == apiSuccessCode)
                    Response.Success(response.body()!!.body)
                else Response.Error(Exception("Error"))
            },
            errorMessage = "Error occurred"
        )


}