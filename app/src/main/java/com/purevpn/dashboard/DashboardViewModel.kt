package com.purevpn.dashboard

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.atom.sdk.android.*
import com.atom.sdk.android.data.model.countries.Country
import com.atom.sdk.android.data.model.protocol.Protocol
import com.atom.sdk.android.exceptions.AtomException
import com.purevpn.BaseViewModel
import com.purevpn.core.enums.ConnectionState
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.launch
import org.koin.standalone.inject
import org.modelmapper.ModelMapper


class DashboardViewModel(application: Application) : BaseViewModel(application), VPNStateListener {


    private val atomManager: AtomManager by inject()
    override fun onConnected() {

    }

    override fun onDisconnected(p0: Boolean) {

    }

    override fun onConnecting() {

    }

    override fun onPacketsTransmitted(p0: String?, p1: String?) {

    }


    override fun onConnected(connectionDetails: ConnectionDetails?) {
        Log.e("Connected", "Called")
        connectionState.set(ConnectionState.CONNECTED)
        connectionState.notifyChange()
    }

    override fun onDialError(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onDisconnected(p0: ConnectionDetails?) {
        connectionState.set(ConnectionState.DISCONNECTED)

    }

    override fun onRedialing(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onStateChange(states: String?) {

        val currentVpnStatus = atomManager.getCurrentVpnStatus(getApplication())

        if (currentVpnStatus == "CONNECTING") {
            connectionState.set(ConnectionState.CONNECTING)
        }

    }


    private val countryService: ICountryService by inject()


    val index = ObservableInt(0)
    val animationFile = ObservableField<String>("anim/disconnected.json")
    val connectionState = ObservableField<ConnectionState>(ConnectionState.DISCONNECTED)

    var progressbarVisibility = ObservableBoolean(true)


    var adapter = CountryAdapter(this)
    var countryObservableList: ObservableField<List<CountryModel>> = ObservableField()


    fun registerCallbacks() {
        AtomManager.addVPNStateListener(this)
        atomManager.bindIKEVStateService(getApplication())
    }

    fun unregisterCallback() {
        AtomManager.removeVPNStateListener(this)
        atomManager.unBindIKEVStateService(getApplication())

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
            name = "UDP"
            number = 9
            isMultiport = false
        }

        val country = ModelMapper().map(countryObject, Country::class.java)
        val builder = VPNProperties.Builder(country, protocol).withSmartDialing().build()
        atomManager.connect(getApplication(), builder)
        index.set(0)
        index.notifyChange()
        animationFile.set("anim/connecting.json")


    }


}