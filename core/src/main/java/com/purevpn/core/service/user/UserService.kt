package com.purevpn.core.service.user

import com.purevpn.core.network.user.models.IpLocationModel
import com.purevpn.core.repository.user.entity.UserEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface UserService {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
    fun getUserPublicIp(url:String): Deferred<Response<IpLocationModel>>

}