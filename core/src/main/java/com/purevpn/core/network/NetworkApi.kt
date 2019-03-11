package com.purevpn.core.network

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NetworkApi {

    @GET
    @Headers("X-Psk: FGtbAXMT4QBBLQj97jvP")
    fun getIp(@Url endpoint: String, @QueryMap params: Map<String, String>): Deferred<Response<JsonElement>>


    @GET
    fun callGetRequest(@Url url: String, @QueryMap params: Map<String, String>,@Header("X-Psk") psk: String): Deferred<Response<JsonObject>>





}