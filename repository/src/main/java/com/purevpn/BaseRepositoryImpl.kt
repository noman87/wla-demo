package com.purevpn

import com.purevpn.core.enums.DatabaseOperations
import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.ApiException
import com.purevpn.core.exceptions.RepoException
import com.purevpn.core.iRepository.IBaseRepository
import com.purevpn.core.models.Result
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
    fun <T : RealmObject> insert(any: Any, classOfT: Class<T>): Result<Boolean> {
        val database = getDatabase()
        val realmObject = convertToRealmObject(any, classOfT)
        when (realmObject) {
            is Result.Success -> {
                try {
                    database.apply {
                        beginTransaction()
                        insertOrUpdate(realmObject.data)
                        commitTransaction()
                        return Result.Success(true)
                    }

                } catch (e: Exception) {
                    return Result.Error(ApiException(Errors.RepoErrorCodes._3002, e))
                }

            }
            is Result.Error -> {
                return Result.Error(realmObject.exception)
            }
        }

    }

    private fun <T : RealmObject> convertToRealmObject(any: Any, classOfT: Class<T>): Result<RealmObject> {
        return try {
            Result.Success(ModelMapper().map<RealmObject>(any, classOfT))
        } catch (e: Exception) {
            return Result.Error(ApiException(Errors.RepoErrorCodes._3001, e))
        }
    }

    fun insertAll(any: Any, type: Type): Result<Boolean> {
        val database = getDatabase()
        try {
            val realmObject = ModelMapper().map<ArrayList<RealmObject>>(any, type)
            try {
                database.apply {
                    beginTransaction()
                    insertOrUpdate(realmObject)
                    commitTransaction()
                    return Result.Success(true)
                }
            } catch (e: Exception) {
                return Result.Error(RepoException(Errors.RepoErrorCodes._3003, e))
            }

        } catch (e: Exception) {
            return Result.Error(RepoException(Errors.RepoErrorCodes._3001, e))
        }

    }


    fun <DATA_CLASS : RealmModel, DATA, RESULT> findAll(
        queryModel: QueryModel<DATA_CLASS, DATA>,
        type: Type
    ): Result<List<RESULT>> {
        val query = getQuery(queryModel)
        when (query) {
            is Result.Success -> {
                try {
                    val allResult = query.data.findAll()
                    val realmList = realm.copyFromRealm(allResult)
                    return Result.Success(ModelMapper().map(realmList, type))
                } catch (e: Exception) {
                    return Result.Error(RepoException(Errors.RepoErrorCodes._3006, e))
                }
            }
            is Result.Error -> {
                return Result.Error(query.exception)
            }
        }

    }


    fun <DATA_CLASS : RealmModel, DATA, RESULT> find(
        queryModel: QueryModel<DATA_CLASS, DATA>,
        type: Type
    ): Result<RESULT> {
        val query = getQuery(queryModel)
        when (query) {
            is Result.Success -> {
                try {
                    val result = query.data.findFirst()
                    result?.apply {
                        val resultObject = realm.copyFromRealm(result)
                        return Result.Success(ModelMapper().map(resultObject, type))
                    }
                    return Result.Error(RepoException(Errors.RepoErrorCodes._3007, null))
                } catch (e: Exception) {
                    return Result.Error(RepoException(Errors.RepoErrorCodes._3007, e))

                }

            }
            is Result.Error -> {
                return Result.Error(query.exception)
            }
        }
    }

    private fun <DATA_CLASS : RealmModel, DATA> getQuery(queryModelList: QueryModel<DATA_CLASS, DATA>): Result<RealmQuery<DATA_CLASS>> {
        try {
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
            return Result.Success(query)
        } catch (e: Exception) {
            return Result.Error(ApiException(Errors.RepoErrorCodes._3001, e))

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