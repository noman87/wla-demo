package com.purevpn.core.service

import com.purevpn.core.Result
import com.purevpn.core.models.LocationModel

interface ILocationService {
    suspend fun getLocation(): Result<LocationModel?>
}