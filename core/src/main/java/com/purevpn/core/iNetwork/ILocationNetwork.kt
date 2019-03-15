package com.purevpn.core.iNetwork

import com.purevpn.core.models.LocationModel

interface ILocationNetwork : IBaseNetwork {
    suspend fun getLocation(params:HashMap<String,String>): LocationModel?
}