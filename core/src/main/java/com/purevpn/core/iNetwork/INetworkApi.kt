package com.purevpn.core.iNetwork

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface INetworkApi {


    @GET
    fun callGetRequest(
        @Url url: String, @QueryMap params: Map<String, String>, @HeaderMap headers: HashMap<String, String>
    ): Deferred<Response<ResponseBody>>


}