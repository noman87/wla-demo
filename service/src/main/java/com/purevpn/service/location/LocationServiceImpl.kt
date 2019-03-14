package com.purevpn.service.location

import com.purevpn.core.Result
import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.ILocationNetwork
import com.purevpn.core.service.ILocationService

class LocationServiceImpl(private val locationRepo: LocationDao, private val iLocationNetwork: ILocationNetwork) :
    ILocationService {

    override suspend fun getLocation(): Result<LocationModel?> {
        val map = HashMap<String, String>()
        map["method"] = "json"
        val publicApi = iLocationNetwork.getLocation(map)
        return when (publicApi) {
            is Result.Success -> {
                val ipLocationModel = publicApi.data
                if (ipLocationModel != null) {
                    //locationRepo.insertLocation(ipLocationModel)
                }
                Result.Success(ipLocationModel)
            }
            is Result.Error -> {
                Result.Error(Exception("Error"))
            }
        }


    }

}