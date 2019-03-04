package com.purevpn.user


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.service.user.UserService
import javax.inject.Inject


class UserViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var  userService:UserService




}