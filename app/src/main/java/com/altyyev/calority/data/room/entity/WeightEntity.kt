package com.altyyev.calority.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "weight")
data class WeightEntity(
    @ColumnInfo(name = "uid") var uid: Int?,
    @ColumnInfo(name = "weight") var weight: Float?,
    @ColumnInfo(name = "timestamp") var timeStamp: Date?,
    @ColumnInfo(name = "emoji") var emoji: String?,
    @ColumnInfo(name = "note") var note: String?,
)
