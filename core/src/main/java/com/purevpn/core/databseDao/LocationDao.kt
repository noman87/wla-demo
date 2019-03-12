package com.purevpn.core.databseDao

import androidx.room.Dao
import androidx.room.Insert
import com.purevpn.core.models.LocationModel

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(vararg location: LocationModel)
}