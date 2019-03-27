package com.purevpn.core.helper

import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType


object Utilities {
    fun <T> mapModel(t: T, response: String, type: ParameterizedType): T? {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<T>(type)
        return adapter.fromJson(response)

    }
}