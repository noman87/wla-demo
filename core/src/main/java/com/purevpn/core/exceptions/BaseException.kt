package com.purevpn.core.exceptions

open class BaseException : Exception() {
    var errorCode: Int = 0
    var errorMessage: String? = null
    var exception: Exception? = null
}