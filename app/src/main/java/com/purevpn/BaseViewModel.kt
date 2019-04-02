package com.purevpn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.standalone.KoinComponent

open class BaseViewModel(val app: Application) : AndroidViewModel(app),KoinComponent {

    protected val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    protected val backgroundScope = CoroutineScope(bgDispatcher)
    protected val uiScope = CoroutineScope(uiDispatcher)

}