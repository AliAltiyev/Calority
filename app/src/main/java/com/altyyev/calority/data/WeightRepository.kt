package com.altyyev.calority.data

import com.altyyev.calority.data.room.WeightDao
import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.mapper.WeightEntityMapper
import com.altyyev.calority.domain.uimodel.WeightUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject


class WeightRepository @Inject constructor(private val dao: WeightDao) {

    fun getAllHistory(): Flow<List<WeightUiModel>> = dao.getAll().map { listOfModel ->
        listOfModel.map { model ->
            WeightEntityMapper.mapFromEntity(model)
        }
    }




    //Todo should remove this line code
//    suspend fun insertWeight(weightEntity: WeightEntity) {
//        return dao.insertWeight(weightEntity)
//    }

    fun findWeightByDate(startOfDay: Date, endOfDay: Date): List<WeightUiModel> =
        dao.findWeightByDate(startOfDay, endOfDay).map { model ->
            WeightEntityMapper.mapFromEntity(model)
        }

    suspend fun updateWeight(weightEntity: WeightEntity) {
        dao.updateWeight(weightEntity)
    }
}