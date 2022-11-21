package com.altyyev.calority.data

import com.altyyev.calority.data.room.WeightDao
import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.mapper.WeightEntityMapper
import com.altyyev.calority.domain.uimodel.WeightUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class WeightRepository @Inject constructor(private val dao: WeightDao) {

    fun getAllHistory(): Flow<List<WeightUiModel>> = dao.getAll().map { listOfModel ->
        listOfModel.map { model ->
            WeightEntityMapper.map(model)
        }
    }

    fun insertWeight(weightEntity: WeightEntity) {
        return dao.insert(weightEntity)
    }
}