package com.purevpn.core.network

import com.purevpn.core.Result
import com.purevpn.core.models.LocationModel

interface ILocationNetwork : IBaseNetwork {
    suspend fun getLocation(map:HashMap<String,String>): Result<LocationModel?>
}