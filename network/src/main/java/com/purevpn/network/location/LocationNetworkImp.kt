package com.purevpn.network.location

import com.purevpn.core.Response
import com.purevpn.core.helper.ApiUrls.URL_IP_2_LOCATION
import com.purevpn.core.helper.Constants.API_ACCESS_TOKEN
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.networkHelper.NetworkHelper
import com.purevpn.network.BaseNetworkImp

class LocationNetworkImp(networkHelper: NetworkHelper) : BaseNetworkImp(networkHelper), LocationNetwork {
    override suspend fun getPublicApi(): Response<IpLocationModel?> {
        apiAccessToken = API_ACCESS_TOKEN
        apiUrl = URL_IP_2_LOCATION
        apiSuccessCode = 1
        return get(IpLocationModel::class.java)
    }


}