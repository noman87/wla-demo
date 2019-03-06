package com.purevpn.core.network

import com.purevpn.core.models.IpLocationModel
import kotlinx.coroutines.Deferred

interface Location : BaseNetwork {
    fun getPublicApi(): Deferred<IpLocationModel>
}