package com.purevpn.service.users

import com.purevpn.core.network.UserNetwork
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.repository.user.entity.UserEntity
import com.purevpn.core.service.UserService


class UserServiceImp(var repository: UserRepository, var userNetwork: UserNetwork) :
    UserService {
    override suspend fun insertUser(user: UserEntity) {
        repository.insertUser(user)
    }

    override fun getUserName(): String {
        return "Noman Noor"
    }
    override suspend fun getAllUsers(): List<UserEntity> {
        return repository.getAllUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity {
        return repository.getUserById(id)
    }


}