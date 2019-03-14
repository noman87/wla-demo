package com.purevpn.service.location

import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

class LocationServiceImpl(private val locationRepo: LocationDao, private val locationNetwork: ILocationNetwork) :
    ILocationService {

    override suspend fun getLocation(): Result<LocationModel?> {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val publicApi = locationNetwork.getLocation(params)
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