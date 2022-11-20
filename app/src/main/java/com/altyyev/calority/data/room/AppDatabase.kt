package com.altyyev.calority.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.utils.Constants.DATABASE_VERSION

@TypeConverters(Converters::class)
@Database(entities = [WeightEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao
}