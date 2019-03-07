package com.purevpn.core.service

import com.purevpn.core.repository.user.entity.UserEntity


interface UserService {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity

    fun getUserName(): String
    suspend fun insertUser(user: UserEntity)


}