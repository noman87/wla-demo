package com.purevpn.core.network

import com.purevpn.core.Response
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.LocationModel

interface LocationNetwork : BaseNetwork {
    suspend fun getPublicApi(): Response<ApiEnvelope<LocationModel?>>
}