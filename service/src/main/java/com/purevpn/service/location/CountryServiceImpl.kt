package com.purevpn.service.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.atom.sdk.android.AtomManager
import com.atom.sdk.android.data.callbacks.CollectionCallback
import com.atom.sdk.android.data.model.countries.Country
import com.atom.sdk.android.exceptions.AtomException
import com.google.gson.reflect.TypeToken
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iRepository.ICountryRepository
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import org.modelmapper.ModelMapper

class CountryServiceImpl : ICountryService, KoinComponent {
    override suspend fun insertAllCountries(listOfCountries: List<CountryModel>) {
        countryRepo.insertAllCountries(listOfCountries)
    }


    private val countryRepo: ICountryRepository by inject()
    private val atomManager: AtomManager by inject()
    val listOfCountries: MutableLiveData<List<CountryModel>> by lazy {
        MutableLiveData<List<CountryModel>>()
    }

    override suspend fun getAllCountries(response: IResponse<List<CountryModel>>) {
        val allCountries = countryRepo.getAllCountries()
        if (allCountries != null && allCountries.isNotEmpty()) {
            response.success(allCountries)
        } else {
            atomManager.getCountries(object : CollectionCallback<Country> {
                override fun onSuccess(countryList: MutableList<Country>?) {
                    val listType = object : TypeToken<MutableList<CountryModel>>() {}.type
                    val map = ModelMapper().map<MutableList<CountryModel>>(countryList, listType)
                    map?.run {
                        CoroutineScope(Dispatchers.IO).launch {
                            for (i in 1..100) {
                                insertAllCountries(this@run)
                            }
                        }
                        response.success(this)
                    }

                }

                override fun onNetworkError(atomException: AtomException?) {
                    Log.e("Error", atomException.toString())

                }

                override fun onError(atomException: AtomException?) {
                    Log.e("Error", atomException.toString())


                }

            })
        }

    }

}