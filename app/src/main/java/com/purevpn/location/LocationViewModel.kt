package com.purevpn.location

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.Result
import com.purevpn.core.models.LocationModel
import com.purevpn.core.service.ILocationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LocationViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val ILocationService: ILocationService by inject()

    var locaiton = ObservableField<LocationModel>()

    var ip = ObservableField<String>()
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO


    fun callApi() {
        CoroutineScope(bgDispatcher).launch {
            val userIpLocation = ILocationService.getLocation()
            when (userIpLocation) {
                is Result.Success -> {
                    userIpLocation.data.run {

                    }

                }
                is Result.Error -> {
                    userIpLocation.exception.toString()
                }
            }
        }
    }


}