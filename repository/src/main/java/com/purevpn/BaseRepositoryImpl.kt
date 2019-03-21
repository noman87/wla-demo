package com.purevpn

import com.purevpn.core.iRepository.IBaseRepository
import com.purevpn.core.iRepository.IDatabase
import io.realm.RealmModel
import io.realm.RealmObject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


open class BaseRepositoryImpl : IBaseRepository, KoinComponent {
    override suspend fun <T : RealmModel> findById(columnName: String, id: Int, dataClass: Class<T>): T? {
        return database.findById(columnName, id, dataClass)
    }

    private val database: IDatabase by inject()

    override suspend fun insert(realmObject: RealmObject): Boolean {
        return database.insert(realmObject)
    }


}