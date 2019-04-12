package com.purevpn.core.errors


class Errors {

    @Target(AnnotationTarget.TYPE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ApiErrorCodes {
        companion object {
            val _1001 = 1001
            val _1002 = 1002
            val _1003 = 1003
            val _1004 = 1004
        }
    }


    @Target(AnnotationTarget.TYPE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class AppErrorCodes {
        companion object {
            val _2001 = 2001
            val _2002 = 2002
            val _2003 = 2003
            val _2004 = 2004
        }
    }


    companion object {
        fun getApiErrorMessage(errorCode: @ApiErrorCodes Int): String {
            return when (errorCode) {
                ApiErrorCodes._1001 -> {
                    "Json parsing error"

                }
                ApiErrorCodes._1002 -> {
                    "Api parsing error"
                }
                ApiErrorCodes._1003 -> {
                    "Web client exception"
                }
                ApiErrorCodes._1004 -> {
                    "API error"
                }
                else -> {
                    "General Error"
                }
            }

        }


        fun getApplicationErrorMessage(errorCode: @AppErrorCodes Int): String {
            return when (errorCode) {
                AppErrorCodes._2001 -> {
                    "Json parsing error"

                }
                AppErrorCodes._2002 -> {
                    "Api parsing error"
                }
                AppErrorCodes._2003 -> {
                    "Web client exception"
                }
                AppErrorCodes._2004 -> {
                    "API error"
                }
                else -> {
                    "General Error"
                }
            }

        }
    }
}