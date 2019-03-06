package com.purevpn.core

import com.purevpn.core.models.ApiEnvelope
import com.purevpn.core.models.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter

class ApiConverter<T>(private val converter: Converter<ResponseBody, ApiEnvelope<T>>) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T {
        val response = converter.convert(value)
        if (response?.header?.code != null) {
            if (response.body != null)
                return response.body
        }
        throw ApiError(response?.header?.code, response?.header?.message)
    }
}