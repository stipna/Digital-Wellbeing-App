package com.stephan.screenlock.data.repository

import com.stephan.screenlock.data.local.dao.AppLimitDao
import com.stephan.screenlock.data.local.dao.UsageSessionDao

data class AppWeeklyUsage(
    val packageName: String,
    val dailyMinutes: List<Float>, // Laenge 7, luecklos (Tage ohne Session = 0f)
    val totalMinutes: Float,
    val previousWeekTotalMinutes: Float,
    val dailyLimitMinutes: Int?
)

enum class WeekTrend { UP, DOWN, FLAT }

fun AppWeeklyUsage.trend(): WeekTrend {
    val diff = totalMinutes - previousWeekTotalMinutes
    return when {
        diff > 1f -> WeekTrend.UP
        diff < -1f -> WeekTrend.DOWN
        else -> WeekTrend.FLAT
    }
}

fun AppWeeklyUsage.trendPercent(): Float =
    if (previousWeekTotalMinutes <= 0f) 0f
    else ((totalMinutes - previousWeekTotalMinutes) / previousWeekTotalMinutes) * 100f

/**
 * Rollierendes 7-Tage-Fenster + Vorwoche + Streak.
 * TODO: DAO-gestuetzte Aggregation (getDailyUsageSince, HISTORY_WINDOW_DAYS)
 * gemaess claude/project-setup.md einbauen — hier nur die Signaturen/Helper.
 */
class StatisticsRepository(
    private val usageSessionDao: UsageSessionDao,
    private val appLimitDao: AppLimitDao
) {
    companion object {
        const val HISTORY_WINDOW_DAYS = 60
    }

    // TODO: fun weeklyUsage(): Flow<List<AppWeeklyUsage>>
    // TODO: fun currentStreakDays(): Flow<Int> — zaehlt rueckwaerts ab gestern.
}
