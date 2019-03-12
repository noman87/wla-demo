package com.purevpn.core.repository.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
 class UserEntity(
    @field:PrimaryKey
    var id:Int,
    var name:String)