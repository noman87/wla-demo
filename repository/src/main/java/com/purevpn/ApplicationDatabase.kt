package com.purevpn

import androidx.room.Database
import androidx.room.RoomDatabase
import com.purevpn.core.databseDao.LocationDao
import com.purevpn.core.models.LocationModel

@Database(entities = [LocationModel::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao


}