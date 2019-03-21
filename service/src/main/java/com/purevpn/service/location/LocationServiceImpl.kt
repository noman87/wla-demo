package com.purevpn.service.location

import android.util.Log
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

        val list = listOf(location)

        val myPredicates: (LocationModel?) -> Boolean = {
            it?.city.equals("karachi")
        }







        location?.apply {
            if (code == locationNetwork.apiSuccessCode) {
                val isSuccess = locationRepository.insertLocation(this)

                val findById =
                    locationRepository.findById(::id.name, 7066894809033623473.toInt(), LocationModel::class.java)
                findById.run {
                    Log.e("IP", ip)
                }
                Log.d("IsSuccess", "" + isSuccess)
                return this
            }

        }
        return null

    }

}