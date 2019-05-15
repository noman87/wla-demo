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


    @Target(AnnotationTarget.TYPE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class RepoErrorCodes {
        companion object {
            val _3001 = 3001
            val _3002 = 3002
            val _3003 = 3003
            val _3004 = 3004
            val _3005 = 3005
            val _3006 = 3006
            val _3007 = 3007
            val _3008 = 3008
        }
    }


    companion object {

        fun getRepoErrorMessage(errorCode: @AppErrorCodes Int): String {
            return when (errorCode) {
                RepoErrorCodes._3001 -> {
                    "Realm object conversion fail"

                }
                RepoErrorCodes._3002 -> {
                    "Insertion fail"
                }
                RepoErrorCodes._3003 -> {
                    "List of insertion fail"
                }
                RepoErrorCodes._3004 -> {
                    "creation of query fail"
                }
                RepoErrorCodes._3005 -> {
                    "creation of query fail"
                }
                RepoErrorCodes._3006 -> {
                    "getting error while finding list of data"
                }
                RepoErrorCodes._3007 -> {
                    "getting error while finding data"
                }
                RepoErrorCodes._3008 -> {
                    "No data found"
                }
                else -> {
                    "General Error"
                }
            }

        }

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