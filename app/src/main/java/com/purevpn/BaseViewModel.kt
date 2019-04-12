package com.purevpn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.atom.sdk.android.AtomManager
import com.atom.sdk.android.ConnectionDetails
import com.atom.sdk.android.VPNStateListener
import com.atom.sdk.android.exceptions.AtomException
import com.purevpn.core.enums.ConnectionState
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

open class BaseViewModel(val app: Application) : AndroidViewModel(app), KoinComponent, VPNStateListener {
    val atomManager: AtomManager by inject()


    var connectionStateObservable: PublishSubject<ConnectionState> = PublishSubject.create<ConnectionState>()


    override fun onConnected() {
    }

    override fun onDisconnected(p0: Boolean) {

    }

    override fun onConnecting() {

    }

    override fun onPacketsTransmitted(p0: String?, p1: String?) {

    }


    override fun onConnected(connectionDetails: ConnectionDetails?) {
        connectionStateObservable.onNext(ConnectionState.CONNECTED)
        Log.e("Connected", "Called")

    }

    override fun onDialError(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onDisconnected(p0: ConnectionDetails?) {

        connectionStateObservable.onNext(ConnectionState.DISCONNECTED)


    }

    override fun onRedialing(p0: AtomException?, p1: ConnectionDetails?) {

    }

    override fun onStateChange(states: String?) {
        val currentVpnStatus = atomManager.getCurrentVpnStatus(getApplication())
        if (currentVpnStatus == "CONNECTING") {
            connectionStateObservable.onNext(ConnectionState.CONNECTING)
        }


    }


    override fun onCleared() {
        unregisterCallback()
        super.onCleared()

    }


    fun registerCallbacks() {
        AtomManager.addVPNStateListener(this)
        atomManager.bindIKEVStateService(getApplication())

        Log.e("CurrentStatus", atomManager.getCurrentVpnStatus(getApplication()))
    }

    fun unregisterCallback() {
        AtomManager.removeVPNStateListener(this)
        atomManager.unBindIKEVStateService(getApplication())

    }

    protected val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    protected val backgroundScope = CoroutineScope(bgDispatcher)
    protected val uiScope = CoroutineScope(uiDispatcher)


}