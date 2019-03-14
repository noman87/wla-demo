package com.purevpn.network

import com.purevpn.core.helper.ApiUrls
import com.purevpn.core.helper.Constants
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

class LocationNetworkImpl : BaseNetworkImpl(), ILocationNetwork {


    override val apiSuccessCode: Int
        get() = Constants.SUCCESS_CODE_ONE


    override suspend fun getLocation(params: HashMap<String, String>): Result<LocationModel?> {
        val headers = HashMap<String, String>()
        headers.put(Constants.X_PSK_KEY, Constants.X_PSK_KEY_VALUE)
        val response = get(ApiUrls.IP_2_LOCATION, params, headers, LocationModel::class.java)
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