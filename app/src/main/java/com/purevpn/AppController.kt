package com.purevpn


import android.app.Application
import android.util.Log
import com.atom.sdk.android.AtomConfiguration
import com.atom.sdk.android.AtomManager
import com.atom.sdk.android.ConnectionDetails
import com.atom.sdk.android.VPNStateListener
import com.atom.sdk.android.exceptions.AtomException
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.enums.ConnectionState
import com.purevpn.core.helper.ApiUrls.BASE_URL
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iNetwork.INetworkApi
import com.purevpn.core.iRepository.IBaseRepository
import com.purevpn.core.iRepository.ICountryRepository
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.iService.ILocationService
import com.purevpn.network.BaseNetworkImpl
import com.purevpn.network.LocationNetworkImpl
import com.purevpn.service.location.CountryServiceImpl
import com.purevpn.service.location.LocationServiceImpl
import io.reactivex.subjects.PublishSubject
import io.realm.Realm
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class AppController : Application(), VPNStateListener {

    lateinit var atomManager: AtomManager


    var connectionStateObservable: PublishSubject<ConnectionState> = PublishSubject.create<ConnectionState>()


    override fun onConnected() {
    }

    override fun onDisconnected(p0: Boolean) {

    }

    override fun onConnecting() {

    }

    override fun onPacketsTransmitted(p0: String?, p1: String?) {

    }


    override fun onConnected(connectionDetails: ConnectionDetails?) {
        connectionStateObservable.onNext(ConnectionState.CONNECTED)
        Log.e("Connected", "Called")

    }

    override fun onDialError(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onDisconnected(p0: ConnectionDetails?) {

        connectionStateObservable.onNext(ConnectionState.DISCONNECTED)


    }

    override fun onRedialing(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onStateChange(states: String?) {
        val currentVpnStatus = atomManager.getCurrentVpnStatus(
            this
        )
        if (currentVpnStatus == "CONNECTING") {
            connectionStateObservable.onNext(ConnectionState.CONNECTING)
        }


    }

    fun registerCallbacks() {
        AtomManager.addVPNStateListener(this)
        //   atomManager.bindIKEVStateService(this)

        Log.e("CurrentStatus", atomManager.getCurrentVpnStatus(this))
    }

    private fun unregisterCallback() {
        AtomManager.removeVPNStateListener(this)
        try {
            atomManager.unBindIKEVStateService(this)
        } catch (ex: Exception) {

        }


    }


    private val applicationMainModule = module {

        single { makeRetrofitService() }

        factory<ILocationNetwork> { LocationNetworkImpl() }

        factory<ILocationService> { LocationServiceImpl() }

        single { WebRequestHelper() }

        single<IBaseNetwork> { BaseNetworkImpl() }

        factory<ILocationRepository> { LocationRepositoryImpl() }

        factory<IBaseRepository> { BaseRepositoryImpl() }

        single { atomManager }

        factory<ICountryRepository> { CountryRepositoryImpl() }
        factory<ICountryService> { CountryServiceImpl() }


    }

    init {
        instance = this
    }

    companion object {

        private var instance: AppController? = null
        fun getInstance(): AppController {
            return instance as AppController
        }
    }


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin(this, listOf(applicationMainModule))
        initAtomSdk()


    }


    private fun initAtomSdk() {
        val build = AtomConfiguration.Builder(getString(R.string.atom_secret_key)).build()
        build?.run {
            AtomManager.initialize(this@AppController, this) {
                atomManager = it
                this@AppController.registerCallbacks()

            }
        }
    }

    private fun makeRetrofitService(): INetworkApi {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(null)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build().create(INetworkApi::class.java)
    }

}