package com.purevpn.database

import com.purevpn.database.entity.User

interface Database {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: Int): User
}