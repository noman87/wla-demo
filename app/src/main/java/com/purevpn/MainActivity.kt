package com.purevpn


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.purevpn.databinding.ActivityMainBinding
import com.purevpn.user.UserViewModel


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.viewmodel = userViewModel



    }
}
