package com.altyyev.calority.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.altyyev.calority.data.room.WeightDao
import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.mapper.WeightEntityMapper
import com.altyyev.calority.domain.uimodel.WeightUiModel
import javax.inject.Inject


class WeightRepository @Inject constructor(private val dao: WeightDao) {

    fun getAllHistory(): LiveData<List<WeightUiModel>> {
        return dao.getAll().map { entity ->
            WeightEntityMapper.mapList(entity)
        }
    }

    fun insertWeight(weightEntity: WeightEntity) {
        return dao.insert(weightEntity)
    }
}