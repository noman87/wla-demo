package com.purevpn.core.iRepository

import com.purevpn.core.enums.DatabaseOperations
import io.realm.RealmObject

interface IBaseRepository {

    suspend fun insert(realmObject: RealmObject): Boolean
    suspend fun <T:RealmObject> find(columnName: String, data: String, dataClass: Class<T>,dbOperation:DatabaseOperations): T?


    suspend fun <T:RealmObject> findAll(columnName: String, data: String, dataClass: Class<T>,dbOperation:DatabaseOperations): List<T>
    suspend fun <T:RealmObject> findAll(dataClass: Class<T>): List<T>



}