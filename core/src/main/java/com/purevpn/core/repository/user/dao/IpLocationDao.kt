package com.purevpn.core.repository.user.dao

import androidx.room.Dao
import androidx.room.Insert
import com.purevpn.core.models.IpLocationModel

@Dao
interface IpLocationDao {

    @Insert
    suspend fun insertLocation(vararg location: IpLocationModel)
}