package com.purevpn.core.network.user.models

import com.squareup.moshi.Json


/**
 * IpLocationModel
 */
class IpLocationModel(
    @Json(name = "ip")
    private var ip: String? = null,
    @Json(name = "city")
    var city: String? = null,
    @Json(name = "country")
    var country: String? = null,
    @Json(name = "iso2")
    var iso2: String? = null,
    @Json(name = "isp")
    var isp: String? = null

)
