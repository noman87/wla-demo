package com.purevpn.core.exceptions

class ApiException(errorCode: Int, errorMessage: String, ex: Exception) : BaseException(errorCode, errorMessage, ex)