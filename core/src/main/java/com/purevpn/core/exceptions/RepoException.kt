package com.purevpn.core.exceptions

import com.purevpn.core.errors.Errors

class RepoException(var errorCode: Int, var exception: Exception?) : Exception() {

    override val message: String?
        get() = Errors.getApiErrorMessage(errorCode)


}