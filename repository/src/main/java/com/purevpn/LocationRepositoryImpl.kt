package com.purevpn

import com.google.gson.reflect.TypeToken
import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.models.LocationModel
import com.purevpn.models.LocationRepoModel
import org.modelmapper.ModelMapper

class LocationRepositoryImpl : BaseRepositoryImpl(), ILocationRepository {

    override suspend fun insertLocation(location: LocationModel): Boolean {
        return insert(ModelMapper().map(location, LocationRepoModel::class.java))
    }

    override suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel> {
        val database = getDatabase()
        val realmResults = database.where(LocationRepoModel::class.java).equalTo(LocationRepoModel::iso2.name, isoCode).and()
            .equalTo(LocationModel::ip.name, ipAddress).findAll()
        val realmList = database.copyFromRealm(realmResults)
        closeDatabase(database)
        val listType = object : TypeToken<List<LocationModel>>() {}.type
        return ModelMapper().map(realmList, listType)

    }


    override suspend fun findAllLocationsByCountry(countryName: String, dbOperation: DatabaseOperations): List<LocationModel> {
        val realmList = findAll(LocationModel::country.name, countryName, LocationRepoModel::class.java, dbOperation)
        val listType = object : TypeToken<List<LocationModel>>() {}.type
        return ModelMapper().map(realmList, listType)


    }

    override suspend fun findAllLocations(dbOperation: DatabaseOperations?): List<LocationModel> {
        val realmList = findAll(LocationRepoModel::class.java)
        val listType = object : TypeToken<List<LocationModel>>() {}.type
        return ModelMapper().map(realmList, listType)
    }


}