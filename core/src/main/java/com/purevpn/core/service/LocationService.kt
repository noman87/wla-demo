package com.purevpn.core.service

import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel

interface LocationService {
    suspend fun getUserIpLocation(): Response<IpLocationModel?>
}