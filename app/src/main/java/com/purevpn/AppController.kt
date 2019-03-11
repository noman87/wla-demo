package com.purevpn


import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.BusinessService
import com.purevpn.core.Controller
import com.purevpn.core.helper.ApiUrls.BASE_URL
import com.purevpn.core.network.BaseNetwork
import com.purevpn.core.network.LocationNetwork
import com.purevpn.core.network.NetworkApi
import com.purevpn.core.networkHelper.NetworkHelper
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.service.LocationService
import com.purevpn.core.service.UserService
import com.purevpn.network.BaseNetworkImp
import com.purevpn.network.location.LocationNetworkImp
import com.purevpn.service.location.LocationServiceImp
import com.purevpn.service.users.UserServiceImp
import com.purevpn.user.UserRepositoryImp
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppController : Application() {

    private val applicationMainModule = module {
        single {
            Room.databaseBuilder(androidApplication(), ApplicationDatabase::class.java, "locations.db")
                .fallbackToDestructiveMigration()
                .build()
        }
        single { RetrofitFactory.makeRetrofitService() }

        single<UserService> { UserServiceImp(get(), get()) }


        single<UserRepository> { UserRepositoryImp(get()) }

        single { get<ApplicationDatabase>().userDao() }

        single { Controller(get()) }

        single { BusinessService() }

        single<LocationNetwork> { LocationNetworkImp(get()) }

        single<LocationService> { LocationServiceImp(get()) }

        single { NetworkHelper(get()) }

        single<BaseNetwork> { BaseNetworkImp(get()) }



        //viewModel { UserViewModel(get()) }

    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationMainModule))


    }

    object RetrofitFactory {
        fun makeRetrofitService(): NetworkApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(NetworkApi::class.java)
        }
    }

}