package com.purevpn


import android.app.Application
import com.atom.sdk.android.AtomConfiguration
import com.atom.sdk.android.AtomManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
import io.realm.Realm
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit


class AppController : Application() {

    lateinit var atomManager: AtomManager


    private val applicationMainModule = module {

        single { RetrofitFactory.makeRetrofitService() }

        single<ILocationNetwork> { LocationNetworkImpl() }

        single<ILocationService> { LocationServiceImpl() }

        single { WebRequestHelper() }

        single<IBaseNetwork> { BaseNetworkImpl() }

        single<ILocationRepository> { LocationRepositoryImpl() }

        single<IBaseRepository> { BaseRepositoryImpl() }

        single { atomManager }

        factory<ICountryRepository>{CountryRepositoryImpl()}
        factory<ICountryService>{CountryServiceImpl()}



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

            }
        }
    }

    object RetrofitFactory {
        fun makeRetrofitService(): INetworkApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(INetworkApi::class.java)
        }
    }

}