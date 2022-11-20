package com.altyyev.calority.ui.add

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altyyev.calority.R
import com.altyyev.calority.data.WeightRepository
import com.altyyev.calority.data.room.entity.WeightEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddWeightViewModel @Inject constructor(private val repository: WeightRepository) :
    ViewModel() {

    sealed class Event {
        object PopBackStack : Event()
        data class Toast(@StringRes private val text: Int) : Event()
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun insertWeight(weight: String, note: String, timeStamp: Date) {
        viewModelScope.launch(Dispatchers.IO) {
            when {
                weight.isBlank() || note.isBlank() -> {
                    eventChannel.send(Event.Toast(R.string.select_date))
                }
                else -> {
                    repository.insertWeight(
                        WeightEntity(
                            weight = weight,
                            note = note,
                            timeStamp = timeStamp,
                            emoji = "Emoji"
                        )
                    )
                    eventChannel.send(
                        Event.PopBackStack
                    )
                }
            }
        }
    }
}