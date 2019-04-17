package com.purevpn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.atom.sdk.android.AtomManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

open class BaseViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {
    val atomManager: AtomManager by inject()



    override fun onCleared() {
        super.onCleared()


    }

    protected val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    protected val backgroundScope = CoroutineScope(bgDispatcher)
    protected val uiScope = CoroutineScope(uiDispatcher)


}