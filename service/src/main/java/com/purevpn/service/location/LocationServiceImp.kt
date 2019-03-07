package com.purevpn.service.location

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
                publicApi.data
            }
            is Response.Error -> {
                publicApi.exception
                this.location.apiErrorMessage
            }
        }
        return publicApi

    }


}