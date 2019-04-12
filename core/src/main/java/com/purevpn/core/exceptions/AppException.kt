package com.purevpn.core.exceptions

import com.purevpn.core.errors.Errors.Companion.getApplicationErrorMessage

open class AppException(var errorCode: Int, var exception: Exception?) : Exception() {

    override val message: String?
        get() = getApplicationErrorMessage(errorCode)

    var apiException: ApiException? = null
    var repoException: RepoException? = null

}