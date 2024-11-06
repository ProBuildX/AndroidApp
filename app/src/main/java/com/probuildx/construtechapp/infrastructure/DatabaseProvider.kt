package com.probuildx.construtechapp.infrastructure

import android.content.Context
import androidx.room.Room
import com.probuildx.construtechapp.infrastructure.database.AppDatabase

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "constructech_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
