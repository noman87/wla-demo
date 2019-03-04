package com.purevpn.di.module

import com.purevpn.ApplicationDatabase
import com.purevpn.core.repository.user.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDaoModule {

    @Singleton
    @Provides
    fun provideUserDao(database: ApplicationDatabase): UserDao {
        return database.userDao()
    }
}