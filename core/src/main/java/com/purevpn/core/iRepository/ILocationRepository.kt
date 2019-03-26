package com.purevpn.core.iRepository

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.models.LocationModel

interface ILocationRepository {


    suspend fun insertLocation(location: LocationModel): Boolean
    suspend fun findAllLocationsByCountry(countryName: String, dbOperation: DatabaseOperations): List<LocationModel>

    suspend fun findAllLocations(dbOperation: DatabaseOperations?): List<LocationModel>

    suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel>


}