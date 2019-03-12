package com.purevpn.core.repository

import com.purevpn.core.models.LocationModel

interface LocationRepository {

    suspend fun insertLocation(location: LocationModel)
}