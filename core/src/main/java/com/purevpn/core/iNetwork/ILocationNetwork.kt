package com.purevpn.core.iNetwork

import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result

interface ILocationNetwork : IBaseNetwork {
    suspend fun getLocation(params:HashMap<String,String>): Result<LocationModel?>
}