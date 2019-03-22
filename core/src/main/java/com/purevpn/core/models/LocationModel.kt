package com.purevpn.core.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


/**
 * LocationModel
 */

open  class LocationModel(

    @PrimaryKey
    var id:Int = UUID.randomUUID().mostSignificantBits.toInt(),
    var ip: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("iso2")
    var iso2: String? = null,
    @SerializedName("isp")
    var isp: String? = null,
    var message: String? = null,
    var code: Int = -1
) : RealmObject()
