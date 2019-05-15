package com.purevpn.core.iRepository

import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

interface ILocationRepository {


    suspend fun insertLocation(location: LocationModel): Result<Boolean>
    suspend fun findAllLocationsByCountry(countryName: String): List<LocationModel>?
    suspend fun findAllLocations(): List<LocationModel>?

    suspend fun findSingleLocationByCountryName(countryName:  String):LocationModel?

    suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel>?


}