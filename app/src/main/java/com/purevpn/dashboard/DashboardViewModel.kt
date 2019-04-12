package com.purevpn.dashboard

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.atom.sdk.android.AtomManager
import com.atom.sdk.android.VPNCredentials
import com.atom.sdk.android.VPNProperties
import com.atom.sdk.android.VPNStateListener
import com.atom.sdk.android.data.model.countries.Country
import com.atom.sdk.android.data.model.protocol.Protocol
import com.purevpn.BaseViewModel
import com.purevpn.core.enums.ConnectionState
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.launch
import org.koin.standalone.inject
import org.modelmapper.ModelMapper


class DashboardViewModel(application: Application) : BaseViewModel(application), VPNStateListener {


    private val countryService: ICountryService by inject()


    val index = ObservableInt(0)
    val animationFile = ObservableField<String>("anim/disconnected.json")
    val connectionState = ObservableField<ConnectionState>(ConnectionState.DISCONNECTED)

    var progressbarVisibility = ObservableBoolean(true)



    var adapter = CountryAdapter(this)
    var countryObservableList: ObservableField<List<CountryModel>> = ObservableField()


    fun init() {
        registerCallbacks()
        var subscribe = connectionStateObservable.subscribe {
            connectionState.set(it)
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

        }

    }

    fun onItemClick(countryObject: CountryModel) {

        connectionState.set(ConnectionState.CONNECTING)
        atomManager.setVPNCredentials(VPNCredentials("purevpn0d583299", "smartdns"))
        val protocol = Protocol()

        protocol.apply {
            name = "IKEV"
            number = 3
            isMultiport = false
        }

        val country = ModelMapper().map(countryObject, Country::class.java)
        val builder = VPNProperties.Builder(country, protocol).withSmartDialing().build()
        atomManager.connect(getApplication(), builder)
        index.set(0)
        index.notifyChange()
        animationFile.set("anim/connecting.json")


    }

    override fun onCleared() {
        super.onCleared()
    }


}