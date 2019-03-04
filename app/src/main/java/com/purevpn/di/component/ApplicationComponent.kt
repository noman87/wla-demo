package com.purevpn.di.component

import android.app.Application
import com.purevpn.di.module.NetworkModule
import com.purevpn.di.module.RepositoryModule
import com.purevpn.di.module.UserDaoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,RepositoryModule::class,UserDaoModule::class])
interface ApplicationComponent{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}

