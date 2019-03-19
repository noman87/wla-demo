package com.purevpn.service.location

import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.LocationModel

class LocationServiceImpl(private val locationRepo: LocationDao, private val locationNetwork: ILocationNetwork) :
    ILocationService {

    override suspend fun getLocation(): LocationModel? {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val location = locationNetwork.getLocation(params)
        location?.apply {
            if (code == locationNetwork.apiSuccessCode)
                return this
        }
        return null

    }

}