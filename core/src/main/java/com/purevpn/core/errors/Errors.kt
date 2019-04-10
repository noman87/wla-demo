    /*
     * Copyrights © 2018 Atom | All Rights Reserved.
     *
     * Last modified 4/3/18 3:27 PM
     */

    package com.purevpn.core.errors

    /**
     * Errors
     */

    object Errors {
        const val _1001 = 1001
        const val _1002 = 1002
        const val _1003 = 1003
        const val _1004 = 1004
        private val errorWithCodesAndMessage = HashMap<Int, String>()

        init {
            errorWithCodesAndMessage[_1001] = "Json parsing error"
            errorWithCodesAndMessage[_1002] = "Api parsing error"
            errorWithCodesAndMessage[_1003] = "Web client exception"
            errorWithCodesAndMessage[_1004] = "API error"

        }

        fun getErrorMessage(errorCode: Int): String {
            if (Errors.errorWithCodesAndMessage[errorCode] != null)
                Errors.errorWithCodesAndMessage[errorCode]?.apply {
                    return this
                }
            return "General error"
        }


    }
