package com.purevpn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class BaseViewModel(application: Application) :AndroidViewModel(application) {

    protected val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
}