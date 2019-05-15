package com.purevpn

import com.google.gson.reflect.TypeToken
import com.purevpn.core.iRepository.ICountryRepository
import com.purevpn.core.models.CountryModel
import com.purevpn.core.models.Result
import com.purevpn.models.CountryRepoModel
import com.purevpn.models.QueryDataModel
import com.purevpn.models.QueryModel

class CountryRepositoryImpl : BaseRepositoryImpl(), ICountryRepository {

    override suspend fun insertAllCountries(list: List<CountryModel>): Result<Boolean> {
        val listType = object : TypeToken<ArrayList<CountryRepoModel>>() {}.type
        return insertAll(list, listType)

    }

    override suspend fun getAllCountries(): Result<List<CountryModel>> {
        val queryDataModel = QueryDataModel(CountryModel::name.name, null, null)
        val queryModel = QueryModel(CountryRepoModel::class.java, listOf(queryDataModel))
        val listType = object : TypeToken<List<CountryModel>>() {}.type
        return findAll(queryModel, listType)

    }


}
