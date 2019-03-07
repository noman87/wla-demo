package com.purevpn.service

import  com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.network.BaseNetwork

open class BaseService {

    fun <T> finalizedOutputForView(apiEnvelope: ApiEnvelope<T>, baseNetwork: BaseNetwork) {
        if (apiEnvelope.header?.code?.toInt() == baseNetwork.apiSuccessCode.toInt()) {
            apiEnvelope.success = true
        }

    }


}