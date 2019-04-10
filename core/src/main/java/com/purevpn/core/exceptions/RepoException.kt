package com.purevpn.core.exceptions

class RepoException(errorCode: Int, errorMessage: String, ex: Exception) : BaseException(errorCode, errorMessage, ex) {
}