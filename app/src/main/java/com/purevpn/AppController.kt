package com.purevpn

import android.app.Application
import com.purevpn.service.users.UserService
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class AppController:Application() {

    val appModules = module {
       /* single { Users(get()) }
        single { BusinessLogic() }*/

        single { UserService(get()) }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModules))
    }
}