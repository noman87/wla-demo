package com.purevpn.core.network

import com.purevpn.core.network.user.models.IpLocationModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface NetworkApi {

    @GET
    @Headers("X-Psk: FGtbAXMT4QBBLQj97jvP")
    fun getIp(@Url endpoint: String): Deferred<Response<IpLocationModel>>
}