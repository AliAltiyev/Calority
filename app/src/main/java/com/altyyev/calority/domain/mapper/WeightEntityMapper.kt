package com.altyyev.calority.domain.mapper

import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.uimodel.WeightUiModel

object WeightEntityMapper {

    fun mapFromEntity(weightEntity: WeightEntity) = WeightUiModel(
        uid = weightEntity.uid,
        weight = weightEntity.weight.orEmpty(),
        emoji = weightEntity.emoji.orEmpty(),
        note = weightEntity.note.orEmpty(),
        timeStamp = weightEntity.timeStamp
    )

    fun mapToEntity(weightEntity: WeightUiModel) = WeightEntity(
        weight = weightEntity.weight.orEmpty(),
        emoji = weightEntity.emoji.orEmpty(),
        note = weightEntity.note.orEmpty(),
        timeStamp = weightEntity.timeStamp
    )

    fun mapList(list: List<WeightEntity>): List<WeightUiModel> {
        return list.map {
            mapFromEntity(it)
        }
    }
}