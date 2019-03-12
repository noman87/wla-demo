package com.purevpn.location

import com.purevpn.ApplicationDatabase
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.repository.user.dao.IpLocationDao

class LocationRepositoryImp(val database:ApplicationDatabase):IpLocationDao {
    override suspend fun insertLocation(vararg location: IpLocationModel) {
        database.locationDao().insertLocation(*location)
    }
}