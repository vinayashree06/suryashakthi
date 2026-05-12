package com.example.suryashakthi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.suryashakthi.model.EnergyLog

@Dao
interface EnergyDao {

    @Insert
    suspend fun insert(log: EnergyLog)

    @Query("SELECT * FROM energy_logs")
    suspend fun getAllLogs(): List<EnergyLog>
}