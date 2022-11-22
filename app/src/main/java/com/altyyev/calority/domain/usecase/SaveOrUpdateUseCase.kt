package com.altyyev.calority.domain.usecase

import com.altyyev.calority.data.room.WeightDao
import com.altyyev.calority.data.room.entity.WeightEntity
import com.altyyev.calority.utils.endOfDay
import com.altyyev.calority.utils.startOfDay
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SaveOrUpdateUseCase @Inject constructor(private val dao: WeightDao) {

    suspend operator fun invoke(weight: String, note: String, timeStamp: Date) {

        val weightList =
            dao.findWeightByDate(
                startOfDay = timeStamp.startOfDay(),
                endOfDay = timeStamp.endOfDay()
            )

        if (weightList.isEmpty()) {
            Timber.d("Saved")
            dao.insertWeight(
                WeightEntity(
                    weight = weight,
                    timeStamp = timeStamp,
                    note = note,
                    emoji = ""
                )
            )
        } else {
            Timber.d("Saved : Updated")
            dao.updateWeight(

                WeightEntity(
                    uid = weightList.first().uid,
                    weight = weight,
                    timeStamp = timeStamp,
                    note = note,
                    emoji = ""
                )
            )
        }

    }

}