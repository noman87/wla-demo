package com.purevpn



import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.core.network.NetworkApi
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.user.UserRepositoryImp
import com.purevpn.user.UserViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
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
        single<UserRepository> { UserRepositoryImp(get()) }
        single { get<ApplicationDatabase>().userDao() }

    }

    val vmModule = module {
        viewModel {
            UserViewModel(get())
        }
    }


    override fun onCreate() {
        super.onCreate()
        
        //startKoin(this , listOf(applicationMainModule,vmModule))


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