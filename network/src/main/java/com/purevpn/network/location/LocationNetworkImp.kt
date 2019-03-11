package com.purevpn.network.location

import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.networkHelper.NetworkHelper
import com.purevpn.network.BaseNetworkImp

class LocationNetworkImp(networkHelper: NetworkHelper) : BaseNetworkImp(networkHelper), LocationNetwork {
    override suspend fun getPublicApi(): Response<IpLocationModel?> {
        apiAccessToken = "FGtbAXMT4QBBLQj97jvP"
        apiUrl = "ipmanagement/v1/ip2Location"
        apiSuccessCode = 1
        return get(IpLocationModel::class.java)
    }


}