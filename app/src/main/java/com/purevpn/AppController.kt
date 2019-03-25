package com.purevpn


import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.helper.ApiUrls.BASE_URL
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iNetwork.INetworkApi
import com.purevpn.core.iRepository.IBaseRepository
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ILocationService
import com.purevpn.network.BaseNetworkImpl
import com.purevpn.network.LocationNetworkImpl
import com.purevpn.service.location.LocationServiceImpl
import io.realm.Realm
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit


class AppController : Application() {

    private val applicationMainModule = module {

        single { RetrofitFactory.makeRetrofitService() }

        single<ILocationNetwork> { LocationNetworkImpl() }

        single<ILocationService> { LocationServiceImpl(get()) }

        single { WebRequestHelper() }

        single<IBaseNetwork> { BaseNetworkImpl() }

        single<ILocationRepository> { LocationRepositoryImpl() }

        single<IBaseRepository> { BaseRepositoryImpl() }


    }


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin(this, listOf(applicationMainModule))

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