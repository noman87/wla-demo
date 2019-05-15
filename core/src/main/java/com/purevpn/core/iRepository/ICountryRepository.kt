package com.purevpn.core.iRepository

import com.purevpn.core.models.CountryModel
import com.purevpn.core.models.Result

interface ICountryRepository {

    suspend fun getAllCountries(): Result<List<CountryModel>>

    suspend fun insertAllCountries(list: List<CountryModel>): Result<Boolean>
}