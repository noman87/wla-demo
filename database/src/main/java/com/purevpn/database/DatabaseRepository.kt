package com.purevpn.database

import com.purevpn.database.entity.User

class DatabaseRepository(var database: ApplicationDatabase) : Database {
    override suspend fun getAllUsers(): List<User> {
        return database.UserDao().getAllUsers()
    }

    override suspend fun getUserById(id: Int): User {
       return database.UserDao().getUser(id)
    }
}