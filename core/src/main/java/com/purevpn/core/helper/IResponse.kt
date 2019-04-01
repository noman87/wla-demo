package com.purevpn.core.helper

interface IResponse<out T : Any> {
    fun <T> success(data: T)
}