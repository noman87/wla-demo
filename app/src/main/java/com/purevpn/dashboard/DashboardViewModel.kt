package com.purevpn.dashboard

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import com.atom.sdk.android.AtomManager
import com.atom.sdk.android.VPNCredentials
import com.atom.sdk.android.VPNProperties
import com.atom.sdk.android.data.model.countries.Country
import com.atom.sdk.android.data.model.protocol.Protocol
import com.purevpn.AppController
import com.purevpn.BaseViewModel
import com.purevpn.core.enums.ConnectionState
import com.purevpn.core.exceptions.AppException
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.launch
import org.koin.standalone.inject
import org.modelmapper.ModelMapper


class DashboardViewModel(application: Application) : BaseViewModel(application) {


    private val countryService: ICountryService by inject()


    private val animationFile = ObservableField<String>("anim/disconnected.json")
    val connectionState = ObservableField<ConnectionState>(ConnectionState.DISCONNECTED)

    var progressbarVisibility = ObservableBoolean(true)


    var adapter = CountryAdapter(this)
    var countryObservableList: ObservableField<List<CountryModel>> = ObservableField()

    var fragments: List<Fragment> = listOf(DashboardFragment(), CountryFragment())
    var fragmentsName: List<String> =
        listOf(app.getString(com.purevpn.R.string.dasboard), app.getString(com.purevpn.R.string.country))


    val index = ObservableInt(fragmentsName.indexOf(app.getString(com.purevpn.R.string.dasboard)))

    fun init() {
        var subscribe = AppController.getInstance().connectionStateObservable.subscribe {

            when (it) {
                ConnectionState.CONNECTED -> {
                    getUserLocation()
                    Log.e("Status", it.name)

                }
                ConnectionState.DISCONNECTED -> {
                    Log.e("Status", it.name)
                    getUserLocation()
                }
                else -> {

                }
            }
            connectionState.set(it)
        }
    }





    private val locationService: ILocationService by inject()

    var observableIpField = ObservableField<String>()

    private fun getUserLocation() {
        backgroundScope.launch {

            when (val userIpLocation = locationService.getLocation()) {
                is com.purevpn.core.models.Result.Success -> {
                    observableIpField.set(userIpLocation.data.ip)
                }
                is com.purevpn.core.models.Result.Error -> {
                    val exception = userIpLocation.exception as AppException
                    Log.e("Exception", exception.errorCode.toString())
                    Log.e("Exception", exception.message)
                }
            }
        }
    }


    fun getCountries() = backgroundScope.launch {

        countryService.getAllCountries(object : IResponse<List<CountryModel>> {
            override fun <T> success(data: T) {
                val countryList = data as List<CountryModel>
                countryObservableList.set(countryList)
                progressbarVisibility.set(false)
            }


        })


    }


    fun onConnectClick() {
        if (atomManager.getCurrentVpnStatus(getApplication()) == AtomManager.VPNStatus.CONNECTED) {
            connectionState.set(ConnectionState.DISCONNECTED)
            atomManager.disconnect(getApplication())

        } else {


        }
    }

    fun onItemClick(countryObject: CountryModel) {

        connectionState.set(ConnectionState.CONNECTING)
        atomManager.setVPNCredentials(VPNCredentials("purevpn0d583299", "smartdns"))
        val protocol = Protocol()

        protocol.apply {
            name = "UDP"
            number = 9
            isMultiport = false
        }

        val country = ModelMapper().map(countryObject, Country::class.java)
        val builder = VPNProperties.Builder(country, protocol).withSmartDialing().build()
        atomManager.connect(getApplication(), builder)
        index.set(fragmentsName.indexOf(app.getString(com.purevpn.R.string.dasboard)))
        index.notifyChange()
        animationFile.set("anim/connecting.json")


    }

    override fun onCleared() {
        super.onCleared()
    }


}