package com.purevpn.service.location

import android.util.Log
import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.network.Location
import com.purevpn.core.service.LocationService
import com.purevpn.service.BaseService

class LocationServiceImp(private val location: Location) : LocationService, BaseService() {
    override suspend fun getUserIpLocation(): Response<IpLocationModel?> {
        val publicApi = location.getPublicApi()

        when (publicApi) {
            is Response.Success -> {
                Log.e("DATA", publicApi.data.toString())

            }
            is Response.Error -> {
                publicApi.exception
            }
        }
        return publicApi

    }


}