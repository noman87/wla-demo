package com.purevpn.network.location

import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.Location
import com.purevpn.core.network.NetworkApi
import com.purevpn.network.BaseNetworkImp
import kotlinx.coroutines.Deferred

class LocationImp(private val api: NetworkApi) : BaseNetworkImp(), Location {

    override fun getPublicApi(): Deferred<IpLocationModel> {

        apiParams = HashMap()
        (apiParams as HashMap<String, String>)["method"] = "json"
        apiUrl = "ipmanagement/v1/ip2Location"
        return api.getIp(apiUrl,apiParams)

    }
}