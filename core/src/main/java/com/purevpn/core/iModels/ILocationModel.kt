package com.purevpn.core.iModels

interface ILocationModel {

    var id: Int
    var ip: String?
    var city: String?
    var country: String?
    var iso2: String?
    var isp: String?
    var message: String?
    var code: Int
}