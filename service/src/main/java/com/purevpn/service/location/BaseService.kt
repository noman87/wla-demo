package com.purevpn.service.location

import com.purevpn.core.errors.Errors

open class BaseService {


    private val errorWithCodesAndMessage = HashMap<Int, String>()

    init {
        errorWithCodesAndMessage[Errors._1001] = "There is a problem please try again"
        errorWithCodesAndMessage[Errors._1002] = "Data is not loading.. try again"
        errorWithCodesAndMessage[Errors._1003] = "Not getting data, try again"
        errorWithCodesAndMessage[Errors._1004] = "Try again"

    }

    fun getErrorMessage(errorCode: Int): String {
        if (errorWithCodesAndMessage.get(errorCode) != null)
            errorWithCodesAndMessage.get(errorCode)?.apply {
                return this
            }
        return "General error"
    }
}