package com.purevpn

import androidx.room.Database
import androidx.room.RoomDatabase
import com.purevpn.core.models.IpLocationModel
import com.purevpn.core.repository.user.dao.IpLocationDao
import com.purevpn.core.repository.user.dao.UserDao
import com.purevpn.core.repository.user.entity.UserEntity

@Database(entities = [UserEntity::class, IpLocationModel::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun locationDao(): IpLocationDao


}