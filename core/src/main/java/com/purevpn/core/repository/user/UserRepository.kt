package com.purevpn.core.repository.user

import com.purevpn.core.repository.user.entity.UserEntity

interface UserRepository {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
    suspend fun insertUser(user:UserEntity)
}