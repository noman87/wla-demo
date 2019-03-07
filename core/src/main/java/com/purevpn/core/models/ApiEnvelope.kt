package com.purevpn.core.models

class ApiEnvelope<T> {
    val header: ApiHeader? = null
    val body: T? = null
    var  success:Boolean = false
}