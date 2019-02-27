package com.purevpn.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.purevpn.database.entity.User

@Dao
interface UserDao {
    @Query("Select * from users")
    suspend fun getAllUsers(): List<User>

    @Query("Select * from users where id = :id ")
    suspend fun getUser(id: Int): User
}