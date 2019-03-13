package com.purevpn.service.location

import com.purevpn.core.Result
import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.service.LocationService

class LocationServiceImp(private val locationRepo: LocationDao, private val locationNetwork: LocationNetwork) :
    LocationService {

    override suspend fun getLocation(): Result<LocationModel?> {
        val map = HashMap<String, String>()
        map["method"] = "json"
        val publicApi = locationNetwork.getLocation(map)
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