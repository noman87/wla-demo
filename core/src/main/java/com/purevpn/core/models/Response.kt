package com.purevpn.core.models

sealed class Response<out T:Any?> {

    data class Data<out T:Any?>(val data:T): Response<T>()
}