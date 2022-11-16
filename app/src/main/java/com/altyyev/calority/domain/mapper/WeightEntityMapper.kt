package com.altyyev.calority.domain.mapper

import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.utils.orZero

object WeightEntityMapper {

    fun map(weightEntity: WeightEntity) = WeightUiModel(
        uid = weightEntity.uid,
        weight = weightEntity.weight.orZero(),
        emoji = weightEntity.emoji.orEmpty(),
        note = weightEntity.note.orEmpty(),
        timeStamp = weightEntity.timeStamp
    )
}