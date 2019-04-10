package com.purevpn.core.exceptions

open class AppException(errorCode: Int, errorMessage: String, ex: Exception) :
    BaseException(errorCode, errorMessage, ex) {
    var apiException: ApiException? = null
    var repoException: RepoException? = null
}