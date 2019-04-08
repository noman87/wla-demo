package com.purevpn

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.iRepository.IBaseRepository
import com.purevpn.models.QueryModel
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmQuery
import org.koin.standalone.KoinComponent
import org.modelmapper.ModelMapper
import java.lang.reflect.Type


open class BaseRepositoryImpl : IBaseRepository, KoinComponent {


    lateinit var realm: Realm

    fun <T : RealmObject> insert(any: Any, classOfT: Class<T>): Boolean {
        val database = getDatabase()
        database.beginTransaction()
        val realmObject = ModelMapper().map<RealmObject>(any, classOfT)
        val copyToRealmOrUpdate = database.copyToRealmOrUpdate(realmObject)
        database.commitTransaction()
        return copyToRealmOrUpdate.isValid
    }

    fun insertAll(any: Any, type: Type): Boolean {
        val database = getDatabase()
        database.beginTransaction()
        val realmObject = ModelMapper().map<ArrayList<RealmObject>>(any, type)
        val copyToRealmOrUpdate = database.copyToRealmOrUpdate(realmObject)
        database.commitTransaction()
        copyToRealmOrUpdate?.apply {
            if (!this.isEmpty())
                return copyToRealmOrUpdate[0].isValid
        }
        return false

    }


    fun <DATA_CLASS : RealmModel, DATA, RESULT> findAll(
        queryModel: QueryModel<DATA_CLASS, DATA>,
        type: Type
    ): List<RESULT>? {
        val query = getQuery(queryModel)
        val allResult = query.findAll()
        val realmList = realm.copyFromRealm(allResult)
        return ModelMapper().map(realmList, type)

    }


    fun <DATA_CLASS : RealmModel, DATA, RESULT> find(
        queryModel: QueryModel<DATA_CLASS, DATA>,
        type: Type
    ): RESULT? {
        val query = getQuery(queryModel)
        val result = query.findFirst()
        result?.apply {
            val resultObject = realm.copyFromRealm(result)
            return ModelMapper().map(resultObject, type)
        }
        return null
    }

    private fun <DATA_CLASS : RealmModel, DATA> getQuery(queryModelList: QueryModel<DATA_CLASS, DATA>): RealmQuery<DATA_CLASS> {
        val database = getDatabase()
        val query = database.where(queryModelList.dataClass)
        for (list in queryModelList.dataList) {
            list.dbOperation?.run {
                this.logicalOperations?.run {
                    when (this) {
                        DatabaseOperations.LogicalOperations.AND -> query.and()
                        DatabaseOperations.LogicalOperations.OR -> query.or()
                        DatabaseOperations.LogicalOperations.CONTAINS -> TODO()
                    }
                }

                this.selectOperations?.run {
                    when (this) {
                        DatabaseOperations.SelectOperations.EQUAL_TO -> {
                            list.data?.apply {
                                when (this) {
                                    is String -> {
                                        query.equalTo(list.columnName, list.data.toString())
                                    }
                                    is Int -> {
                                        query.equalTo(list.columnName, list.data.toString().toInt())
                                    }
                                }
                            }


                        }

                        DatabaseOperations.SelectOperations.NOT_EQUAL_TO -> {

                        }
                    }
                }


            }

        }
        return query


    }


    protected fun getDatabase(): Realm {
        realm = Realm.getDefaultInstance()
        return realm
    }

    protected fun closeDatabase(realm: Realm) {
        realm.close()
    }


}