package com.purevpn.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.AppController.RetrofitFactory.BASE_URL
import com.purevpn.core.network.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
     fun provideApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
     fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}