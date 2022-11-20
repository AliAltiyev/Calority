package com.altyyev.calority.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.altyyev.calority.data.room.entity.WeightEntity
import java.util.*

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight")
    fun getAll(): LiveData<List<WeightEntity>>

    @Query("SELECT * FROM weight WHERE timestamp  = :timeStamp")
    fun findWeightByOnDate(timeStamp: Date): List<WeightEntity>

    @Insert
    fun insertAll(vararg weight: WeightEntity)

    @Insert
    fun insert(weight: WeightEntity)

    @Delete
    fun delete(weight: WeightEntity)


}