package com.purevpn.core.iRepository

import io.realm.RealmModel
import io.realm.RealmObject

interface IBaseRepository {
    suspend fun insert(realmObject: RealmObject):Boolean
    suspend fun<T : RealmModel> findById(columnName:String, id:Int, dataClass: Class<T>):T?

}