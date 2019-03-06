package com.purevpn.network.user

import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.NetworkApi
import com.purevpn.core.network.UserNetwork
import kotlinx.coroutines.Deferred


class UserNetworkApiImp(private val api: NetworkApi) : UserNetwork {
    override fun getUserPublicIpAsync(endpoint: String,params: Map<String, String>): Deferred<IpLocationModel> {

        return api.getIp(endpoint,params)
    }

}