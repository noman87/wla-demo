package com.purevpn.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val loginModel = LoginModel()
        loginModel.userName = "please enter you user name"
        binding.userData = loginModel
        binding.viewModel = viewModel



    }
}
