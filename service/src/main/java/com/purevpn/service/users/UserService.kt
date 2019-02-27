package com.purevpn.service.users

import com.purevpn.database.Database

class UserService(var database: Database) : User {
    override suspend fun getAllUsers(): List<com.purevpn.database.entity.User> {
        return database.getAllUsers()
    }

    override suspend fun getUserById(id: Int): com.purevpn.database.entity.User {
        return database.getUserById(id)
    }


}