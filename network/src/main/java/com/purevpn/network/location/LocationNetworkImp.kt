package com.purevpn.network.location

import com.purevpn.core.Result
import com.purevpn.core.helper.ApiUrls
import com.purevpn.core.helper.Constants
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.networkHelper.NetworkHelper
import com.purevpn.network.BaseNetworkImp

class LocationNetworkImp(networkHelper: NetworkHelper) : BaseNetworkImp(networkHelper), LocationNetwork {
    override suspend fun getPublicApi(): Result<ApiEnvelope<LocationModel?>> {
        apiAccessToken = Constants.API_ACCESS_TOKEN
        apiUrl = ApiUrls.URL_IP_2_LOCATION
        apiSuccessCode = 1

        return get(LocationModel::class.java)
    }


}