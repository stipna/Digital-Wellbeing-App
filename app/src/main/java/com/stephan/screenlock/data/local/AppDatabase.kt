package com.stephan.screenlock.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stephan.screenlock.data.local.dao.AppLimitDao
import com.stephan.screenlock.data.local.dao.BlockedAppDao
import com.stephan.screenlock.data.local.dao.UsageSessionDao
import com.stephan.screenlock.data.local.entity.AppLimit
import com.stephan.screenlock.data.local.entity.BlockedAppEntity
import com.stephan.screenlock.data.local.entity.UsageSessionEntity

@Database(
    entities = [BlockedAppEntity::class, AppLimit::class, UsageSessionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blockedAppDao(): BlockedAppDao
    abstract fun appLimitDao(): AppLimitDao
    abstract fun usageSessionDao(): UsageSessionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "screenlock.db"
                )
                    // TODO: fallbackToDestructiveMigration() ist nur fuer die
                    // Dev-Phase gedacht — vor Release durch echte
                    // Migration(1, 2) ersetzen (siehe claude/project-setup.md).
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
