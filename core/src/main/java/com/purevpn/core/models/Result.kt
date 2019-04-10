package com.purevpn.core.models

import com.purevpn.core.exceptions.AppException

sealed class Result<out T:Any?> {

    data class Success<out T:Any?>(val data:T): Result<T>()
    data class Error(val exception:AppException): Result<Nothing>()
}