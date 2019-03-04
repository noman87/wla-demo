package com.purevpn.user

import com.purevpn.ApplicationDatabase
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.repository.user.entity.UserEntity
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(var database: ApplicationDatabase) : UserRepository {
    override suspend fun getAllUsers(): List<UserEntity> {
        return database.userDao().getAllUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity {
       return database.userDao().getUser(id)
    }
}