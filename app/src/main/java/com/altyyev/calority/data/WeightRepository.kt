package com.altyyev.calority.data

import com.altyyev.calority.data.room.WeightDao
import com.altyyev.calority.domain.mapper.WeightEntityMapper
import com.altyyev.calority.domain.uimodel.WeightUiModel
import javax.inject.Inject

class WeightRepository @Inject constructor(private val dao: WeightDao) {

    fun getAllHistory(): List<WeightUiModel> {
        return dao.getAll().map {
            WeightEntityMapper.map(it)
        }
    }

}