package com.purevpn.core.network

import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel

interface Location  {
    suspend fun getPublicApi(): Response<IpLocationModel?>
}