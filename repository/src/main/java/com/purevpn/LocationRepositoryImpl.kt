package com.purevpn

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.models.LocationModel

class LocationRepositoryImpl : BaseRepositoryImpl(), ILocationRepository {
    override suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel> {
        val database = getDatabase()
        val realmResults = database.where(LocationModel::class.java).equalTo(LocationModel::iso2.name, isoCode).and()
            .equalTo(LocationModel::ip.name, ipAddress).findAll()
        val list = database.copyFromRealm(realmResults)
        closeDatabase(database)
        return list
    }


    override suspend fun findAllLocationsByCountry(
        countryName: String,
        dbOperation: DatabaseOperations
    ): List<LocationModel> {
        return findAll(LocationModel::country.name, countryName, LocationModel::class.java, dbOperation)
    }

    override suspend fun findAllLocations(dbOperation: DatabaseOperations?): List<LocationModel> {
        return findAll(LocationModel::class.java)
    }

    override suspend fun insertLocation(location: LocationModel): Boolean {
        return insert(location)
    }


}