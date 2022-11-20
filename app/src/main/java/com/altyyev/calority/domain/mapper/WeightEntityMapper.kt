package com.altyyev.calority.domain.mapper

import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.uimodel.WeightUiModel

object WeightEntityMapper {

    fun map(weightEntity: WeightEntity) = WeightUiModel(
        uid = weightEntity.uid,
        weight = weightEntity.weight.orEmpty(),
        emoji = weightEntity.emoji.orEmpty(),
        note = weightEntity.note.orEmpty(),
        timeStamp = weightEntity.timeStamp
    )

    fun mapList(list: List<WeightEntity>): List<WeightUiModel> {
        return list.map {
            map(it)
        }
    }
}