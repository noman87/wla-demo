package com.purevpn.di.module

import android.content.Context
import com.purevpn.ApplicationDatabase
import com.purevpn.user.UserRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {
    @Provides
    @Reusable
    @JvmStatic
     fun provideUserRepo(database: ApplicationDatabase): UserRepositoryImp {
        return UserRepositoryImp(database)
    }


    @Provides
    @Reusable
    @JvmStatic
     fun provideApplicationDatabase(context: Context): ApplicationDatabase {
        return ApplicationDatabase.buildDatabase(context)
    }
}