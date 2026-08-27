package com.stephan.screenlock.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stephan.screenlock.data.local.entity.UsageSessionEntity
import kotlinx.coroutines.flow.Flow

data class DailyUsageRow(
    val packageName: String,
    val day: String, // yyyy-MM-dd
    val minutes: Float
)

@Dao
interface UsageSessionDao {
    @Insert
    suspend fun insert(session: UsageSessionEntity)

    // Aggregiert Tagessummen pro App, lokale Mitternacht.
    // Bekannte Einschraenkung: Sessions ueber Mitternacht werden komplett dem
    // Starttag zugerechnet (siehe claude/project-setup.md).
    @Query(
        """
        SELECT packageName,
               strftime('%Y-%m-%d', startTimestamp / 1000, 'unixepoch', 'localtime') AS day,
               SUM(endTimestamp - startTimestamp) / 60000.0 AS minutes
        FROM usage_sessions
        WHERE startTimestamp >= :sinceEpochMillis
        GROUP BY packageName, day
        """
    )
    fun getDailyUsageSince(sinceEpochMillis: Long): Flow<List<DailyUsageRow>>
}
