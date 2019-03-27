package com.purevpn.network

import com.purevpn.core.helper.ApiUrls
import com.purevpn.core.helper.Constants
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.LocationModel
import com.squareup.moshi.Types

class LocationNetworkImpl : BaseNetworkImpl(), ILocationNetwork {


    override val apiSuccessCode: Int
        get() = Constants.SUCCESS_CODE_ONE


    override suspend fun getLocation(params: HashMap<String, String>): LocationModel? {
        val headers = HashMap<String, String>()
        headers[Constants.X_PSK_KEY] = Constants.X_PSK_KEY_VALUE
        val parameterizedType = Types.newParameterizedType(ApiEnvelope::class.java, LocationModel::class.java)
        val response = get(ApiUrls.IP_2_LOCATION, params, headers, ApiEnvelope<LocationModel>(), parameterizedType)


        response?.run {
            return body?.apply {
                header?.let {
                    this.code = it.code
                    this.message = it.message
                }
            }
        }
        return null
    }

}