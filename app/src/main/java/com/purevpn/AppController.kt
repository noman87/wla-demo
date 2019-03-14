package com.purevpn


import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.BusinessService
import com.purevpn.core.Controller
import com.purevpn.core.helper.ApiUrls.BASE_URL
import com.purevpn.core.network.IBaseNetwork
import com.purevpn.core.network.ILocationNetwork
import com.purevpn.core.network.INetworkApi
import com.purevpn.core.networkHelper.WebRequestHelper
import com.purevpn.core.repository.ILocationRepository
import com.purevpn.core.service.ILocationService
import com.purevpn.location.ILocationRepositoryImp
import com.purevpn.network.IBaseNetworkImp
import com.purevpn.network.location.LocationNetworkImpl
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

        single { Controller(get()) }

        single { BusinessService() }

        single<ILocationNetwork> { LocationNetworkImpl(get()) }

        single<ILocationService> { LocationServiceImpl(get(), get()) }

        single { WebRequestHelper(get()) }

        single<IBaseNetwork> { IBaseNetworkImp(get()) }

        single { RepositoryHelper(get()) }
        single<ILocationRepository> { ILocationRepositoryImp(get()) }

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