package com.purevpn

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iRepository.IBaseRepository
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import org.koin.standalone.KoinComponent


open class BaseRepositoryImpl : IBaseRepository, KoinComponent {


    override suspend fun <T : RealmObject> findAll(dataClass: Class<T>): List<T> {
        val realmResult = getQuery(null, null, dataClass, null).findAll()
        val list = realm.copyFromRealm(realmResult)
        closeDatabase(realm)
        return list
    }


    override suspend fun <T : RealmObject> findAll(
        columnName: String,
        data: String,
        dataClass: Class<T>,
        dbOperation: DatabaseOperations
    ): List<T> {
        val realmResult = getQuery(columnName, data, dataClass, dbOperation).findAll()
        val list = realm.copyFromRealm(realmResult)
        closeDatabase(realm)
        return list
    }


    lateinit var realm: Realm


    override suspend fun insert(realmObject: RealmObject): Boolean {
        val database = getDatabase()
        database.beginTransaction()
        val copyToRealmOrUpdate = database.copyToRealmOrUpdate(realmObject)
        database.commitTransaction()
        return copyToRealmOrUpdate.isValid


    }


    override suspend fun <T : RealmObject> find(
        columnName: String, data: String, dataClass: Class<T>, dbOperation: DatabaseOperations
    ): T? {
        val realmResult = getQuery(columnName, data, dataClass, dbOperation).findFirst()
        val obj = realm.copyFromRealm(realmResult)
        closeDatabase(realm)
        return obj
    }


    private fun <T : RealmObject> getQuery(
        columnName: String?,
        data: String?,
        dataClass: Class<T>,
        dbOperation: DatabaseOperations?
    ): RealmQuery<T> {

        return when (dbOperation) {
            DatabaseOperations.EQUAL_TO -> {
                getDatabase().where(dataClass).equalTo(columnName, data)
            }

            DatabaseOperations.NOT_EQUAL_TO -> {
                getDatabase().where(dataClass).notEqualTo(columnName, data)
            }
            null -> {
                getDatabase().where(dataClass)
            }
        }


    }


    protected fun getDatabase(): Realm {
        realm = Realm.getDefaultInstance()
        return realm
    }

    protected fun closeDatabase(realm: Realm) {
        realm.close()
    }


}