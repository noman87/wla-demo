package com.purevpn.network

import com.google.gson.reflect.TypeToken
import com.purevpn.core.helper.ApiUrls
import com.purevpn.core.helper.Constants
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.LocationModel

class LocationNetworkImpl : BaseNetworkImpl(), ILocationNetwork {


    override val apiSuccessCode: Int
        get() = Constants.SUCCESS_CODE_ONE


    override suspend fun getLocation(params: HashMap<String, String>): LocationModel? {
        val headers = HashMap<String, String>()
        headers[Constants.X_PSK_KEY] = Constants.X_PSK_KEY_VALUE
        val collectionType = object : TypeToken<ApiEnvelope<LocationModel>>() {}.type
        val response = get(ApiUrls.IP_2_LOCATION, params, headers, collectionType)
        response?.run {
            val apiEnvelope = response as ApiEnvelope<LocationModel>
            return apiEnvelope.body?.apply {
                apiEnvelope.header?.let {
                    this.code = it.code
                    this.message = it.message
                }
            }
        }
        return null
    }

}