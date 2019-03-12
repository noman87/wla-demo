package com.purevpn.service.location

import com.purevpn.core.Response
import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.service.LocationService
import com.purevpn.service.BaseService

class LocationServiceImp(private val locationRepo: LocationDao, private val location: LocationNetwork) :
    LocationService, BaseService() {
    override suspend fun getUserIpLocation(): Response<LocationModel?> {
        location.apiParams = HashMap()
        location.apiParams["method"] = "json"
        val publicApi = location.getPublicApi()
        return when (publicApi) {
            is Response.Success -> {
                val ipLocationModel = publicApi.data.body
                if (ipLocationModel != null) {
                    locationRepo.insertLocation(ipLocationModel)
                }

                Response.Success(ipLocationModel)
            }
            is Response.Error -> {
                Response.Error(Exception("Error"))
            }
        }


    }

}