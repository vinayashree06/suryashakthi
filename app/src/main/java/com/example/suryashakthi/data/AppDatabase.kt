package com.example.suryashakthi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.suryashakthi.model.EnergyLog

@Database(entities = [EnergyLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun energyDao(): EnergyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "energy_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}