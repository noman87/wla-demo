package com.purevpn.location

import com.purevpn.RepositoryHelper
import com.purevpn.core.models.LocationModel
import com.purevpn.core.repository.ILocationRepository

class ILocationRepositoryImp(private val repositoryHelper: RepositoryHelper) : ILocationRepository {

    override suspend fun insertLocation(location: LocationModel) {
        repositoryHelper.insertLocation(location)
    }

}