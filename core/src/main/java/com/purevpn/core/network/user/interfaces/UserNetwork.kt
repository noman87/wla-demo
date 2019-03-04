package com.purevpn.core.network.user.interfaces

import com.purevpn.core.network.user.models.IpLocationModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Url

interface UserNetwork {
     fun getUserPublicIpAsync(@Url endpoint: String): Deferred<Response<IpLocationModel>>

}