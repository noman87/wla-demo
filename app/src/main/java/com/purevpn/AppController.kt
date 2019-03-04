package com.purevpn



import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.network.NetworkApi
import com.purevpn.network.user.UserNetworkApiImp
import com.purevpn.service.users.UserServiceImp
import com.purevpn.user.UserRepositoryImp
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AppController : Application() {

    private val applicationMainModule = module {
        single {
            Room.databaseBuilder(get(), ApplicationDatabase::class.java, "locations.db")
                .fallbackToDestructiveMigration()
                .build()
        }
        single {
            RetrofitFactory.makeRetrofitService()
        }
        single {
            UserServiceImp(get(),get())
        }
        single {
            UserNetworkApiImp(get())
        }
        single { UserRepositoryImp(get()) }

        single { get<ApplicationDatabase>().userDao() }

    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this , listOf(applicationMainModule))


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