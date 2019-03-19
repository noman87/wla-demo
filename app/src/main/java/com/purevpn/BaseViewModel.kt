package com.purevpn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    protected val backgroundScope = CoroutineScope(bgDispatcher)
    protected val uiScope = CoroutineScope(uiDispatcher)

}