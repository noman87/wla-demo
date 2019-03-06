package com.purevpn.core

import com.purevpn.core.models.ApiEnvelope
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiConverterFactory(private val factory: MoshiConverterFactory) : Converter.Factory() {
    /**
     * Returns a [Converter] for converting an HTTP response body to `type`, or null if
     * `type` cannot be handled by this factory. This is used to create converters for
     * response types such as `SimpleResponse` from a `Call<SimpleResponse>`
     * declaration.
     */
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {

        val wrappedType = object : ParameterizedType {
            override fun getRawType(): Type {
                return ApiEnvelope::class.java
            }

            override fun getOwnerType(): Type? {
                return null
            }

            override fun getActualTypeArguments(): Array<Type> {
                return arrayOf(type)
            }
        }

        val moshiConverter : Converter<ResponseBody, *>? = factory
            .responseBodyConverter(wrappedType, annotations, retrofit)

        return ApiConverter(moshiConverter as Converter<ResponseBody, ApiEnvelope<Any>>)
    }
}