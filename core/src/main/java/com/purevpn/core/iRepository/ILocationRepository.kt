package com.purevpn.core.iRepository

import com.purevpn.core.models.LocationModel

interface ILocationRepository {

    suspend fun insertLocation(location: LocationModel)
}