package com.purevpn.login

import android.app.Application
import android.util.Log
import com.purevpn.BaseViewModel

class LoginViewModel(app: Application) : BaseViewModel(app) {

    fun onButtonClick(model: LoginModel) {

        Log.e("Data", model.userName)
    }
}