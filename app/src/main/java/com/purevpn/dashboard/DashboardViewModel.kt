package com.purevpn.dashboard

import android.app.Application
import com.purevpn.BaseViewModel
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.launch
import org.koin.standalone.inject

class DashboardViewModel(application: Application) : BaseViewModel(application) {


    private val countryService: ICountryService by inject()


    lateinit var list: List<CountryModel>
    fun getCountries() = backgroundScope.launch {
        countryService.getAllCountries(object : IResponse<List<CountryModel>> {
            override fun <T> success(data: T) {
                list = data as List<CountryModel>
            }

        })


    }


}