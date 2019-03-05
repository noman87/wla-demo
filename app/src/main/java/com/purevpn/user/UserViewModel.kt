package com.purevpn.user


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.repository.user.entity.UserEntity
import com.purevpn.core.service.user.UserService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class UserViewModel(application: Application) : AndroidViewModel(application), KoinComponent {


    private val service: UserService by inject()



    suspend fun insertNewUser(userEntity: UserEntity) {
        service.insertUser(userEntity)
    }

}