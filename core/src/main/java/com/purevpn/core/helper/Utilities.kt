package com.purevpn.core.helper

import com.google.gson.Gson
import java.lang.reflect.Type


object Utilities {
    fun <T> mapModel(response: String, type:Type): T? {
        return Gson().fromJson(response, type)
    }
}