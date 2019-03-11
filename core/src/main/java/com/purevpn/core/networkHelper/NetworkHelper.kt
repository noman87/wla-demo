package com.purevpn.core.networkHelper

import com.purevpn.core.Common
import com.purevpn.core.Response
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.network.NetworkApi
import org.json.JSONObject

class NetworkHelper(var networkApi: NetworkApi) {

    suspend fun <T> get(baseNetwork: BaseNetwork, classOfT: Class<T>): Response<T?> {
        return try {
            val apiResponse =
                networkApi.callGetRequest(baseNetwork.apiUrl, baseNetwork.apiParams, baseNetwork.apiAccessToken).await()

            val response = apiResponse.body().toString()
            val jsonObject: JSONObject = JSONObject(response)
            val header = jsonObject.getJSONObject("header")


            if (header != null && header is JSONObject && header.getString("code").toInt() == baseNetwork.apiSuccessCode) {
                val body = jsonObject.getJSONObject("body")
                if (body != null && body is JSONObject) {
                    val objectFromGSON = Common.getObjectFromGSON(body.toString(), classOfT)
                    Response.Success(objectFromGSON)
                } else {
                    Response.Error(java.lang.Exception("Error"))
                }

            } else {
                Response.Error(java.lang.Exception("Error"))
            }
        } catch (e: Exception) {
            Response.Error(java.lang.Exception("Error"))
        }


    }

    suspend fun <T> post(baseNetwork: BaseNetwork, classOfT: Class<T>): Response<T?> {
        return try {

            val apiResponse =
                networkApi.callGetRequest(baseNetwork.apiUrl, baseNetwork.apiParams, baseNetwork.apiAccessToken).await()
            val response = apiResponse.body().toString()
            val jsonObject: JSONObject = JSONObject(response)
            val header = jsonObject.getJSONObject("header")
            if (header != null && header is JSONObject && header.getString("code").toInt() == baseNetwork.apiSuccessCode) {
                val body = jsonObject.getJSONObject("body")
                if (body != null && body is JSONObject) {
                    val objectFromGSON = Common.getObjectFromGSON(body.toString(), classOfT)
                    Response.Success(objectFromGSON)
                } else {
                    Response.Error(java.lang.Exception("Error"))
                }

            } else {
                Response.Error(java.lang.Exception("Error"))
            }
        } catch (e: Exception) {
            Response.Error(java.lang.Exception("Error"))
        }


    }

}