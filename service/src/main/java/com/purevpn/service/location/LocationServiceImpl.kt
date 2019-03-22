package com.purevpn.service.location

import android.util.Log
import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.LocationModel
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LocationServiceImpl(private val locationNetwork: ILocationNetwork) :
    ILocationService, KoinComponent {
    private val locationRepository: ILocationRepository by inject()
    override suspend fun getLocation(): LocationModel? {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val location = locationNetwork.getLocation(params)

        location?.apply {
            if (code == locationNetwork.apiSuccessCode) {
                val isSuccess = locationRepository.insertLocation(this)

                val locationModel =
                    locationRepository.findAllLocationsByCountry("Pakistan", DatabaseOperations.EQUAL_TO)

                locationModel.run {
                    Log.e("IP", ip)
                }
                return this
            }

        }
        return null

    }

}