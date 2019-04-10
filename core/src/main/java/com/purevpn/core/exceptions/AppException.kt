package com.purevpn.core.exceptions

open class AppException : BaseException() {
    var apiException: ApiException? = null


    companion object {

        fun makeApiException(errorCode: Int, errorMessage: String, exception: Exception): AppException {
            val appException = AppException()
            appException
                .apply {
                    val apiException = ApiException().apply {
                        this.errorCode = errorCode
                        this.errorMessage = errorMessage
                        this.exception = exception
                    }

                    this.apiException = apiException
                }


            return appException
        }
    }


}