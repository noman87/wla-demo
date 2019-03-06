package com.purevpn.core.network.user.interfaces

import com.purevpn.core.network.user.models.IpLocationModel
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface UserNetwork {
    fun getUserPublicIpAsync(endpoint: String, params: Map<String, String>): Deferred<Response<IpLocationModel>>

}