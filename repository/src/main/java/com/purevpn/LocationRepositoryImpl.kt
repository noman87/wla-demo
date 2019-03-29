package com.purevpn

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.models.LocationModel
import com.purevpn.models.LocationRepoModel
import com.purevpn.models.QueryDataModel
import com.purevpn.models.QueryModel

class LocationRepositoryImpl : BaseRepositoryImpl(), ILocationRepository {

    override suspend fun insertLocation(location: LocationModel): Boolean {
        return insert(location, LocationRepoModel::class.java)
    }

    override suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel>? {

        val databaseOperations = DatabaseOperations()
        databaseOperations.selectOperations = DatabaseOperations.SelectOperations.EQUAL_TO

        val databaseOperationsSecond = DatabaseOperations()
        databaseOperationsSecond.selectOperations = DatabaseOperations.SelectOperations.EQUAL_TO
        databaseOperationsSecond.logicalOperations = DatabaseOperations.LogicalOperations.AND
        val queryOne = QueryDataModel(LocationRepoModel::iso2.name, databaseOperations, isoCode)
        val querySecond = QueryDataModel(LocationRepoModel::ip.name, databaseOperationsSecond, ipAddress)
        val queryModel = QueryModel(LocationRepoModel::class.java, listOf(queryOne, querySecond))


        return findAll(queryModel,LocationModel::class.java)


    }



    override suspend fun findAllLocationsByCountry(countryName: String): List<LocationModel>? {
        val databaseOperations = DatabaseOperations()
        databaseOperations.selectOperations = DatabaseOperations.SelectOperations.EQUAL_TO
        val queryDataModel = QueryDataModel(LocationRepoModel::country.name, databaseOperations, countryName)
        val queryModel = QueryModel(LocationRepoModel::class.java, listOf(queryDataModel))
        val findAll = findAll(queryModel, LocationModel::class.java)
        return findAll


    }

    override suspend fun findAllLocations(): List<LocationModel>? {
        val queryDataModel = QueryDataModel(LocationRepoModel::country.name, null, null)
        val queryModel = QueryModel(LocationRepoModel::class.java, listOf(queryDataModel))
        return findAll(queryModel, LocationModel::class.java)
    }


}