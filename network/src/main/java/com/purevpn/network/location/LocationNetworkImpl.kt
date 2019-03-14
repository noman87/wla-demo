package com.purevpn.network.location

import com.purevpn.core.Result
import com.purevpn.core.helper.ApiUrls
import com.purevpn.core.helper.Constants
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.ILocationNetwork
import com.purevpn.core.networkHelper.WebRequestHelper
import com.purevpn.network.IBaseNetworkImp

class LocationNetworkImpl(webRequestHelper: WebRequestHelper) : IBaseNetworkImp(webRequestHelper), ILocationNetwork {

    override suspend fun getLocation(map: HashMap<String, String>): Result<LocationModel?> {
        val headers = HashMap<String, String>()
        headers.put("X-Psk", Constants.API_ACCESS_TOKEN)
        apiSuccessCode = 1
        val response = get(ApiUrls.URL_IP_2_LOCATION, map, headers, LocationModel::class.java)
        return when (response) {
            is Result.Success -> {
                val responseBody = response.data.body
                responseBody?.message = response.data.header?.message
                Result.Success(responseBody)
            }
            is Result.Error -> {

                Result.Error(Exception("Error"))
            }
        }
    }


}