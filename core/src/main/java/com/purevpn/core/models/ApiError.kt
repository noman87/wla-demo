package com.purevpn.core.models

class ApiError(val errorCode: Int?, val errorMessage: String?) : Throwable(errorMessage) {
}