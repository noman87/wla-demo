package com.purevpn.core.iRepository

import io.realm.RealmModel
import io.realm.RealmObject

interface IDatabase {
    suspend fun insert(obj: RealmObject):Boolean

    suspend fun<T : RealmModel> findById(columnName:String, id:Int, dataClass: Class<T>):T?

}