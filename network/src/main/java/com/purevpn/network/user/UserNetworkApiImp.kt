package com.purevpn.network.user

import com.purevpn.core.network.NetworkApi
import com.purevpn.core.network.user.interfaces.UserNetwork
import com.purevpn.core.network.user.models.IpLocationModel
import kotlinx.coroutines.Deferred
import retrofit2.Response


class UserNetworkApiImp(private val api: NetworkApi) : UserNetwork {
    override fun getUserPublicIpAsync(endpoint: String,params: Map<String, String>): Deferred<Response<IpLocationModel>> {

        return api.getIp(endpoint,params)
    }

}