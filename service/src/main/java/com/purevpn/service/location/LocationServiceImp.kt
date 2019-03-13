package com.purevpn.service.location

import com.purevpn.core.Result
import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.service.LocationService
import com.purevpn.service.BaseService

class LocationServiceImp(private val locationRepo: LocationDao, private val location: LocationNetwork) :
    LocationService, BaseService() {
    override suspend fun getUserIpLocation(): Result<LocationModel?> {
        location.apiParams = HashMap()
        location.apiParams["method"] = "json"
        val publicApi = location.getPublicApi()
        return when (publicApi) {
            is Result.Success -> {
                val ipLocationModel = publicApi.data.body
                if (ipLocationModel != null) {
                    locationRepo.insertLocation(ipLocationModel)
                }

                Result.Success(ipLocationModel)
            }
            is Result.Error -> {
                Result.Error(Exception("Error"))
            }
        }


    }

}