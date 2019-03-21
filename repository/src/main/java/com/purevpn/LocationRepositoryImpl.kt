package com.purevpn

import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.models.LocationModel
import io.realm.RealmModel

class LocationRepositoryImpl : BaseRepositoryImpl(), ILocationRepository {
    override suspend fun insertLocation(location: LocationModel): Boolean {
        return insert(location)
    }

    override suspend fun <T : RealmModel> findById(columnName: String, id: Int, dataClass: Class<T>): T? {
        return super.findById(columnName, id, dataClass)
    }


}