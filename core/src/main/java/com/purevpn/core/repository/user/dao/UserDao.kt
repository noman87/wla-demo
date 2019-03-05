package com.purevpn.core.repository.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.purevpn.core.repository.user.entity.UserEntity

@Dao
interface UserDao {
    @Query("Select * from users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("Select * from users where id = :id ")
    suspend fun getUser(id: Int): UserEntity


    @Insert
    suspend fun insert(vararg users: UserEntity)

}