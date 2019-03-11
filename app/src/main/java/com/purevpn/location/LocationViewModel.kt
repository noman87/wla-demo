package com.purevpn.location

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.Response
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.service.LocationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            when (userIpLocation) {
                is Response.Success -> userIpLocation.data
                is Response.Error -> userIpLocation.exception.toString()
            }
        }
    }


}