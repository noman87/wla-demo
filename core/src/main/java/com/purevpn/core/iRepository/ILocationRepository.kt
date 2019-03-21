package com.purevpn.core.iRepository

import com.purevpn.core.models.LocationModel
import io.realm.RealmModel

interface ILocationRepository {

    suspend fun insertLocation(location: LocationModel):Boolean
    suspend fun<T : RealmModel> findById(columnName:String, id:Int, dataClass: Class<T>):T?
}