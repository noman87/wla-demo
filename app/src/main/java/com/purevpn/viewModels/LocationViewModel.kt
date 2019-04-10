package com.purevpn.viewModels

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import com.purevpn.BaseViewModel
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.Result
import kotlinx.coroutines.launch
import org.koin.standalone.inject

class LocationViewModel(application: Application) : BaseViewModel(application) {

    private val locationService: ILocationService by inject()

    var observableIpField = ObservableField<String>()

    fun getUserLocation() {
        backgroundScope.launch {
            val userIpLocation = locationService.getLocation()
            when (userIpLocation) {
                is Result.Success -> {
                    Log.e("IP", userIpLocation.data.ip)
                    observableIpField.set(userIpLocation.data.ip)
                }
                is Result.Error -> {

                    val apiException = userIpLocation.exception.apiException
                    Log.e("Exception", apiException?.errorCode.toString())
                }
            }
        }
    }


}