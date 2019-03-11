package com.purevpn.core

import com.google.gson.Gson

object Common {

    fun <T> getObjectFromGSON(`object`: String, classOfT: Class<T>): T? {
        val gson = Gson()
        try {
            return gson.fromJson(`object`, classOfT)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}