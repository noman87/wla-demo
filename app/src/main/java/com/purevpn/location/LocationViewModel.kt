package com.purevpn.location

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.service.LocationService
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LocationViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val locationService: LocationService by inject()

    var locaiton = ObservableField<IpLocationModel>()

    var ip = ObservableField<String>()
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO


    fun callApi() {

        CoroutineScope(bgDispatcher).launch {
            val userIpLocation = locationService.getUserIpLocation()
            withContext(uiDispatcher) {
                ip.set(userIpLocation.ip)
            }
        }
    }


}