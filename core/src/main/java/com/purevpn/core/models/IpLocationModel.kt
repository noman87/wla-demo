package com.purevpn.core.models

import com.google.gson.annotations.SerializedName


/**
 * IpLocationModel
 */
data class IpLocationModel(
    var ip: String,
    @SerializedName("city")
    var city: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("iso2")
    var iso2: String,
    @SerializedName("isp")
    var isp: String? = null

)
