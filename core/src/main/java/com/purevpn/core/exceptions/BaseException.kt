package com.purevpn.core.exceptions

open class BaseException(var errorCode: Int, var errorMessage: String, var ex: Exception) : Exception() {

}