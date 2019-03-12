package com.purevpn.location

import com.purevpn.RepositoryHelper
import com.purevpn.core.models.LocationModel
import com.purevpn.core.repository.LocationRepository

class LocationRepositoryImp(private val repositoryHelper: RepositoryHelper) : LocationRepository {

    override suspend fun insertLocation(location: LocationModel) {
        repositoryHelper.insertLocation(location)
    }

}