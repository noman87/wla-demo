package com.purevpn.core.network

import com.purevpn.core.Result
import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.LocationModel

interface LocationNetwork : BaseNetwork {
    suspend fun getPublicApi(): Result<ApiEnvelope<LocationModel?>>
}