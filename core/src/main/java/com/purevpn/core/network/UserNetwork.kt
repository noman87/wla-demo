package com.purevpn.core.network

import com.purevpn.core.models.IpLocationModel
import kotlinx.coroutines.Deferred

interface UserNetwork {
    fun getUserPublicIpAsync(endpoint: String, params: Map<String, String>): Deferred<IpLocationModel>

}