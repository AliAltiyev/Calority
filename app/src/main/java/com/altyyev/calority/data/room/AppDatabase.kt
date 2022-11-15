package com.altyyev.calority.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.altyyev.calority.data.room.entity.WeightEntity

@TypeConverters(Converters::class)
@Database(entities = [WeightEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao
}