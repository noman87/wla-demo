package com.purevpn.di.module

import com.purevpn.core.network.user.interfaces.UserNetwork
import com.purevpn.core.repository.user.UserRepository
import com.purevpn.core.service.user.UserService
import com.purevpn.service.users.UserServiceImp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object UserServiceModule {
    @Provides
    @Reusable
    @JvmStatic
    fun provideUserService(repository: UserRepository, userNetwork: UserNetwork): UserService {
        return UserServiceImp(repository, userNetwork)
    }
}