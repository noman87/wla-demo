package com.purevpn

import com.purevpn.core.iRepository.IDatabase
import com.purevpn.core.models.LocationModel
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject

class DatabaseImpl : IDatabase {


    override suspend fun <T : RealmModel> findById(columnName: String, id: Int, dataClass: Class<T>): T? {

        val realm = Realm.getDefaultInstance()
        val findAll = realm.where(dataClass).equalTo("city", "Karachi").findAll()
        val findAll1 = realm.where(dataClass).equalTo("city", "Karachi").findAll()

        val copyFromRealm = realm.copyFromRealm(findAll)
        var list = copyFromRealm as List<LocationModel>

        return realm.where(dataClass).equalTo(columnName,id).findFirst()


    }






    override suspend fun insert(obj: RealmObject): Boolean {
        val realm = Realm.getDefaultInstance()
        var isSuccess: Boolean = false
        realm.executeTransaction {
            val realmObject = it.copyToRealmOrUpdate(obj)
            isSuccess = realmObject.isValid
        }
        realm.close()
        return isSuccess
    }


}