package com.purevpn.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NavViewModel(app: Application) : AndroidViewModel(app) {

    val items = MutableLiveData<String>()

    fun select(item: String) {
        items.value = item;
    }

}