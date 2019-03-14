package com.purevpn.core.repository

import com.purevpn.core.models.LocationModel

interface ILocationRepository {

    suspend fun insertLocation(location: LocationModel)
}