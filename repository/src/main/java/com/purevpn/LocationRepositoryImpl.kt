package com.purevpn

import com.purevpn.RepositoryHelper
import com.purevpn.core.models.LocationModel
import com.purevpn.core.iRepository.ILocationRepository

class LocationRepositoryImpl(private val repositoryHelper: RepositoryHelper) : ILocationRepository {

    override suspend fun insertLocation(location: LocationModel) {
        repositoryHelper.insertLocation(location)
    }

}