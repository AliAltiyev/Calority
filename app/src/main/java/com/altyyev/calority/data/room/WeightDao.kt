package com.altyyev.calority.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.altyyev.calority.data.room.entity.WeightEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight ORDER by timestamp DESC")
    fun getAll(): Flow<List<WeightEntity>>


    @Query("SELECT * FROM weight WHERE timestamp BETWEEN :startOfDay AND :endOfDay")
    fun findWeightByDate(startOfDay: Date, endOfDay: Date): List<WeightEntity>

    @Insert
    fun insertAll(vararg weight: WeightEntity)

    @Insert
    fun insert(weight: WeightEntity)

    @Delete
    fun delete(weight: WeightEntity)


}