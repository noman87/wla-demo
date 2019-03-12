package com.purevpn

import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel

class RepositoryHelper(private val database:ApplicationDatabase): LocationDao{
    override suspend fun insertLocation(vararg location: LocationModel) {
        database.locationDao()
    }

}