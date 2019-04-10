package com.purevpn.core.iService

import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

interface ILocationService {
    suspend fun getLocation(): Result<LocationModel>

}