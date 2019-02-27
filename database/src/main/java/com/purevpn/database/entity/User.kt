package com.purevpn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @field:PrimaryKey
    var id:Int,
    var name:String) {
}