package com.purevpn.core.iRepository

import com.purevpn.core.models.LocationModel

interface ILocationRepository {


    suspend fun insertLocation(location: LocationModel): Boolean
    suspend fun findAllLocationsByCountry(countryName: String): List<LocationModel>?
    suspend fun findAllLocations(): List<LocationModel>?
    suspend fun findAllLocationByIsoCodeAndIpAddress(isoCode: String, ipAddress: String): List<LocationModel>?


}