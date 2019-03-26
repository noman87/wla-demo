package com.purevpn.core.models

import com.google.gson.annotations.SerializedName
import com.purevpn.core.iModels.ILocationModel


/**
 * LocationModel
 */

class LocationModel : ILocationModel {
    override var id: Int = 0
    @SerializedName("ip")
    override var ip: String? = null
    @SerializedName("city")
    override var city: String? = null
    @SerializedName("country")
    override var country: String? = null
    @SerializedName("iso2")
    override var iso2: String? = null
    @SerializedName("isp")
    override var isp: String? = null
    override var message: String? = null
    override var code: Int = 0



}
