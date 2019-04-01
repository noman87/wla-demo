package com.purevpn.core.iRepository

import com.purevpn.core.models.CountryModel

interface ICountryRepository {

    suspend fun getAllCountries():List<CountryModel>?

    suspend fun insertAllCountries(list: List<CountryModel>):Boolean
}