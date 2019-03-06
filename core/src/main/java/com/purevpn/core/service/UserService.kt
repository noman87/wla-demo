package com.purevpn.core.service

import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.repository.user.entity.UserEntity
import kotlinx.coroutines.Deferred


interface UserService {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
    fun getUserPublicIp(url: String, params: Map<String, String>): Deferred<IpLocationModel>
    fun getUserName(): String
    suspend fun insertUser(user: UserEntity)


}