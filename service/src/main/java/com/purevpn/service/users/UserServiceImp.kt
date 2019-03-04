package com.purevpn.service.users

import com.purevpn.core.network.user.interfaces.UserNetwork
import com.purevpn.core.network.user.models.IpLocationModel
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.repository.user.entity.UserEntity
import com.purevpn.core.service.user.UserService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject


class UserServiceImp @Inject constructor(var repository: UserRepository, var userNetwork: UserNetwork) : UserService {
    override fun getUserPublicIp(url:String): Deferred<Response<IpLocationModel>> {
        return userNetwork.getUserPublicIpAsync(url)
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return repository.getAllUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity {
        return repository.getUserById(id)
    }


}