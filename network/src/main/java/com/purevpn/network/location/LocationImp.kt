package com.purevpn.network.location

import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.network.Location

class LocationImp(private val baseNetwork: BaseNetwork) : Location {

    override suspend fun getPublicApi(): Response<IpLocationModel?> {
        baseNetwork.apiParams = HashMap()
        (baseNetwork.apiParams as HashMap<String, String>)["method"] = "json"
        baseNetwork.apiAccessToken = "FGtbAXMT4QBBLQj97jvP"
        baseNetwork.apiUrl = "ipmanagement/v1/ip2Location"
        baseNetwork.apiSuccessCode = 1
        return baseNetwork.get(IpLocationModel::class.java)
    }

    /*safeApiCall(
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
    )*/


}