package com.purevpn.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.purevpn.BaseViewModel
import com.purevpn.core.helper.IResponse
import com.purevpn.core.iService.ICountryService
import com.purevpn.core.models.CountryModel
import kotlinx.coroutines.launch
import org.koin.standalone.inject

class DashboardViewModel(application: Application) : BaseViewModel(application) {


    private val countryService: ICountryService by inject()


    var list: MutableLiveData<List<CountryModel>> = MutableLiveData()

    fun getCountries() = backgroundScope.launch {

        countryService.getAllCountries(object : IResponse<List<CountryModel>> {
            override fun <T> success(data: T) {
                val countryList = data as List<CountryModel>
                list.postValue(countryList)
            }

        })


    }


}