package com.purevpn.service.location

import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.repository.user.dao.IpLocationDao
import com.purevpn.core.service.LocationService
import com.purevpn.service.BaseService

class LocationServiceImp(private val locationRepo: IpLocationDao, private val location: LocationNetwork) :
    LocationService, BaseService() {
    override suspend fun getUserIpLocation(): Response<IpLocationModel?> {
        location.apiParams = HashMap()
        location.apiParams["method"] = "json"
        val publicApi = location.getPublicApi()
        return when (publicApi) {
            is Response.Success -> {
                val ipLocationModel = publicApi.data.body
                ipLocationModel?.message = publicApi.data.header?.message
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