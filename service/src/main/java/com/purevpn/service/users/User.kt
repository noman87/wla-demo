package com.purevpn.service.users

import com.purevpn.database.entity.User

interface User {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: Int): User
}