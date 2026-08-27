package com.stephan.screenlock.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usage_sessions",
    indices = [Index(value = ["packageName", "startTimestamp"])]
)
data class UsageSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val packageName: String,
    val startTimestamp: Long,
    val endTimestamp: Long
)
