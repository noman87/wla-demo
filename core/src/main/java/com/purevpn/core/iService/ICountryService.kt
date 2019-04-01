package com.purevpn.core.iService

import com.purevpn.core.helper.IResponse
import com.purevpn.core.models.CountryModel

interface ICountryService {

    suspend fun getAllCountries(response:IResponse<List<CountryModel>>)
}