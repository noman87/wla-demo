package com.purevpn.core.iService

import com.purevpn.core.models.LocationModel

interface ILocationService {
    suspend fun getLocation(): LocationModel?
}