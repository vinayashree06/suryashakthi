package com.example.suryashakthi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energy_logs")
data class EnergyLog(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val generated: Double,
    val consumed: Double,
    val savings: Double
)