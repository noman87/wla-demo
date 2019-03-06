package com.purevpn.core.service.user

import com.purevpn.core.network.user.models.IpLocationModel
import com.purevpn.core.repository.user.entity.UserEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface UserService {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
    fun getUserPublicIp(url:String,params: Map<String, String>): Deferred<Response<IpLocationModel>>
    fun getUserName():String
    suspend fun insertUser(user: UserEntity)


}