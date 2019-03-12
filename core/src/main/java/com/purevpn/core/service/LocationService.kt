package com.purevpn.core.service

import com.purevpn.core.Response
import com.purevpn.core.models.LocationModel

interface LocationService {
    suspend fun getUserIpLocation(): Response<LocationModel?>
}