package com.purevpn


import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.helper.ApiUrls.BASE_URL
import com.purevpn.core.helper.WebRequestHelper
import com.purevpn.core.iNetwork.IBaseNetwork
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iNetwork.INetworkApi
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ILocationService
import com.purevpn.network.BaseNetworkImpl
import com.purevpn.network.LocationNetworkImpl
import com.purevpn.service.location.LocationServiceImpl
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit


class AppController : Application() {

    private val applicationMainModule = module {
        single {
            Room.databaseBuilder(androidApplication(), ApplicationDatabase::class.java, "locations.db")
                .fallbackToDestructiveMigration()
                .build()
        }
        single { RetrofitFactory.makeRetrofitService() }


        single { get<ApplicationDatabase>().locationDao() }


        factory<ILocationNetwork> { LocationNetworkImpl() }

        factory<ILocationService> { LocationServiceImpl(get(), get()) }

        single { WebRequestHelper() }

        single<IBaseNetwork> { BaseNetworkImpl() }

        single { RepositoryHelper(get()) }
        single<ILocationRepository> { LocationRepositoryImpl(get()) }

    }


    override fun onCreate() {
        super.onCreate()
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