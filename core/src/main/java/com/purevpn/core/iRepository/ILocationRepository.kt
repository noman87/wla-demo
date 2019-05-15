package com.purevpn.core.iRepository

import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

interface ILocationRepository {


    suspend fun insertLocation(location: LocationModel): Result<Boolean>

    suspend fun findAllLocationsByCountry(countryName: String): Result<List<LocationModel>>

    suspend fun findAllLocations(): Result<List<LocationModel>>

    suspend fun findSingleLocationByCountryName(countryName: String): Result<LocationModel>

    suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): Result<List<LocationModel>>


}