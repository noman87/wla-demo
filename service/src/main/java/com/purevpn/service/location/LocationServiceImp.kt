package com.purevpn.service.location

import android.util.Log
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.Location
import com.purevpn.core.service.LocationService

class LocationServiceImp(private val location: Location) : LocationService {
    suspend override fun getUserIpLocation(): IpLocationModel {
        val response = location.getPublicApi().await()
        Log.e("url", location.apiUrl)
        return response

    }
}