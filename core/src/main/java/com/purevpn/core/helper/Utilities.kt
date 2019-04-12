package com.purevpn.core.helper

import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.ApiException
import com.purevpn.core.models.Result
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType


object Utilities {
    fun <T> mapModel(t: T, response: String, type: ParameterizedType): Result<T> {
        return try {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter<T>(type)
            val fromJson = adapter.fromJson(response)
            if (fromJson != null) {
                Result.Success(fromJson)
            } else {
                val exception =
                    ApiException(Errors.ApiErrorCodes._1001, null)
                Result.Error(exception)
            }

        } catch (e: Exception) {
            val exception =
                ApiException(Errors.ApiErrorCodes._1001, e)
            Result.Error(exception)
        }

    }
}