package com.purevpn.user


import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.purevpn.core.service.user.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class UserViewModel(application: Application) : AndroidViewModel(application), KoinComponent {


    var userName = ObservableField<String>()
    var id = ObservableField<Int>()


    private val service: UserService by inject()

    // dispatches execution into Android main thread
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    // represent a pool of shared threads as coroutine dispatcher
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO


    fun callApi() {
        CoroutineScope(bgDispatcher).launch {
            Log.e("ThreadName",Thread.currentThread().name)
            val hashMap = HashMap<String, String>()
            hashMap.put("method", "json")
            val url = "ip_location.php"
            val request = service.getUserPublicIp(url, hashMap)
            val response = request.await()
            Log.e("Response", response.body()!!.country)




        }
    }

    fun onGetUserClick() {
        CoroutineScope(bgDispatcher).launch {
            val userById = service.getUserById(2)
            userName.set(userById.name)
            id.set(userById.id)
        }


    }


}