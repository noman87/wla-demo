package com.purevpn.models

import com.purevpn.core.iModels.ICountryModel
import io.realm.RealmObject

open class CountryRepoModel : RealmObject(), ICountryModel {
    override var isSmartDns: Int? = 0
    override var latitude: Double? = 0.0
    override var name: String? = null
    override var id: Int? = 0
    override var isoCode: String? = null
    override var longitude: Double? = null
}