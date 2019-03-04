package com.purevpn


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.purevpn.user.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var userViewModel: UserViewModel

    // dispatches execution into Android main thread
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    // represent a pool of shared threads as coroutine dispatcher
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(bgDispatcher).launch {
            userViewModel.userService.getAllUsers()
        }


    }
}
