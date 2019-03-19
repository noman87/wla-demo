package com.purevpn.viewModels

import android.app.Application
import androidx.databinding.ObservableField
import com.purevpn.BaseViewModel
import com.purevpn.core.iService.ILocationService
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LocationViewModel(application: Application) : BaseViewModel(application), KoinComponent {

    private val locationService: ILocationService by inject()
    var observableIpField = ObservableField<String>()

    fun getUserLocation() = backgroundScope.launch {

        val userIpLocation = locationService.getLocation()
        userIpLocation?.ip?.run {
            observableIpField.set(this)
        }
    }


}