package com.purevpn.dilayer

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.purevpn.BaseRepositoryImpl
import com.purevpn.CountryRepositoryImpl
import com.purevpn.LocationRepositoryImpl
import com.purevpn.core.helper.ApiUrls
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

open class DiResolver {


    fun makeRetrofit(): INetworkApi {
        return makeRetrofitService()
    }

    fun makeLocationNetwork(): ILocationNetwork {
        return LocationNetworkImpl()
    }


    fun makeLocationNetworkService(): ILocationService {
        return LocationServiceImpl()
    }

    fun makeLocationRepository(): ILocationRepository {
        return LocationRepositoryImpl()
    }


    fun makeCountryRepo(): ICountryRepository {
        return CountryRepositoryImpl()
    }

    fun makeBaseRepository(): IBaseRepository {
        return BaseRepositoryImpl()
    }

    fun makeBaseNetwork(): IBaseNetwork {
        return BaseNetworkImpl()
    }

    fun makeCountryService(): ICountryService {
        return CountryServiceImpl()
    }


    fun makeRetrofitService(): INetworkApi {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(null)
            .build()
        return Retrofit.Builder()
            .baseUrl(ApiUrls.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build().create(INetworkApi::class.java)
    }

    fun webRequestHelper(): WebRequestHelper {
        return WebRequestHelper()
    }
}
