package com.purevpn.core.helper

import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.AppException
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
                    AppException.makeApiException(Errors._1001, Errors.getErrorMessage(Errors._1001),Exception())
                Result.Error(exception)
            }

        } catch (e: Exception) {
            val exception =
                AppException.makeApiException(Errors._1001, Errors.getErrorMessage(Errors._1001),Exception())
            Result.Error(exception)
        }

    }
}