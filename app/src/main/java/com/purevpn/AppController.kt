package com.purevpn


import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.BusinessService
import com.purevpn.core.Controller
import com.purevpn.core.network.NetworkApi
import com.purevpn.core.network.user.interfaces.UserNetwork
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.service.user.UserService
import com.purevpn.network.user.UserNetworkApiImp
import com.purevpn.service.users.UserServiceImp
import com.purevpn.user.UserRepositoryImp
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AppController : Application() {

    private val applicationMainModule = module {
        single {
            Room.databaseBuilder(androidApplication(), ApplicationDatabase::class.java, "locations.db")
                .fallbackToDestructiveMigration()
                .build()
        }
        single {
            RetrofitFactory.makeRetrofitService()
        }
        single<UserService> {
            UserServiceImp(get(),get())
        }
        single<UserNetwork> {
            UserNetworkApiImp(get())
        }
        single<UserRepository> { UserRepositoryImp(get()) }

        single { get<ApplicationDatabase>().userDao() }

        single { Controller(get()) }
        single { BusinessService() }
        //viewModel { UserViewModel(get()) }

    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationMainModule))


    }

    object RetrofitFactory {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun makeRetrofitService(): NetworkApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(NetworkApi::class.java)
        }
    }

}