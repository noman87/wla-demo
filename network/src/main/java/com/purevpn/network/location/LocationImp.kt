package com.purevpn.network.location

import com.purevpn.core.Response
import com.purevpn.core.network.Location
import com.purevpn.core.network.NetworkApi
import com.purevpn.network.BaseNetworkImp
import java.io.IOException

class LocationImp(private val api: NetworkApi) : BaseNetworkImp(), Location {
    override suspend fun getPublicApi() =
        safeApiCall(
            call = {
                apiParams = HashMap()
                (apiParams as HashMap<String, String>)["method"] = "json"
                apiUrl = "ipmanagement/v1/ip2Location"
                val response = api.getIp(apiUrl, apiParams).await()
                apiErrorMessage = response.message()
                return@safeApiCall if (response.body()?.header?.code == 1)
                    Response.Success(response.body()!!.body)
                else Response.Error(IOException("Error"))
            },
            errorMessage = "Error occurred"
        )


}