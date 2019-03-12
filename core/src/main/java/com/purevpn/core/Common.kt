package com.purevpn.core

import com.google.gson.Gson
import com.purevpn.core.models.ApiEnvelope
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object Common {

    fun <T> getResponse(response: String, dataClass: Class<T>): ApiEnvelope<T?> {
        return Gson().fromJson(response, getType(ApiEnvelope::class.java, dataClass))
    }


    private fun getType(rawClass: Class<*>, parameterClass: Class<*>): Type {
        return object : ParameterizedType {
            override fun getActualTypeArguments(): Array<Type> {
                return arrayOf(parameterClass)
            }

            override fun getRawType(): Type {
                return rawClass
            }

            override fun getOwnerType(): Type? {
                return null
            }

        }
    }


}