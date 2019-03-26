package com.purevpn.models

import com.purevpn.core.iModels.ILocationModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class LocationRepoModel(
    @PrimaryKey
    override var id: Int=0,
    override var ip: String? = null,
    override var city: String? = null,
    override var country: String? = null,
    override var iso2: String? = null,
    override var isp: String? = null,
    override var message: String? = null,
    override var code: Int = 0) : RealmObject(), ILocationModel {


}