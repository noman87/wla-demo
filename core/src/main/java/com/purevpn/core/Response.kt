package com.purevpn.core

sealed class Response<out T:Any> {

    data class success<out T:Any>(val data:T):Response<T>()
    data class Error(val exception:Exception):Response<Nothing>()
}