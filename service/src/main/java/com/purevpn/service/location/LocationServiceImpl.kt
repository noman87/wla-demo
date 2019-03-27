package com.purevpn.service.location

import android.util.Log
import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.LocationModel
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class LocationServiceImpl :
    ILocationService, KoinComponent {

    private val locationRepository: ILocationRepository by inject()
    private val locationNetwork: ILocationNetwork by inject()

    override suspend fun getLocation(): LocationModel? {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val location = locationNetwork.getLocation(params)

        location?.apply {
            if (code == locationNetwork.apiSuccessCode) {
                id = UUID.randomUUID().mostSignificantBits.toInt()
                val isSuccess = locationRepository.insertLocation(this)

                val locationModel =
                    locationRepository.findAllLocationsByCountry("Pakistan", DatabaseOperations.EQUAL_TO)


                val locationModelOtherThanPak =
                    locationRepository.findAllLocationsByCountry("Pakistan", DatabaseOperations.NOT_EQUAL_TO)

                locationModel.run {
                    Log.e("IP", ip)
                }
                return this
            }

        }
        return null

    }

}