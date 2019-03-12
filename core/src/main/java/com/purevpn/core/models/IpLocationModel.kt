package com.purevpn.core.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * IpLocationModel
 */
@Entity(tableName = "tbl_location")
data class IpLocationModel(
    @field:PrimaryKey
    var id: Int,

    var ip: String?,
    @SerializedName("city")
    var city: String?,

    @SerializedName("country")
    var country: String?,

    @SerializedName("iso2")
    var iso2: String?,

    @Ignore
    @SerializedName("isp")
    var isp: String? = null,

    var message: String? = null

) {
    constructor() : this(0, null, null, null, null, null, null)
}
