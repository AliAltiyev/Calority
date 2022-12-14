package com.altyyev.calority.ui.add

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altyyev.calority.R
import com.altyyev.calority.data.WeightRepository
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.domain.usecase.SaveOrUpdateUseCase
import com.altyyev.calority.utils.endOfDay
import com.altyyev.calority.utils.startOfDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddWeightViewModel @Inject constructor(
    private val repository: WeightRepository,
    private val useCase: SaveOrUpdateUseCase
) :
    ViewModel() {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState


    fun insertOrUpdateWeight(weight: String, note: String, timeStamp: Date, emoji: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when {
                weight.isBlank() || note.isBlank() -> {
                    eventChannel.send(Event.Toast(R.string.select_date))
                }
                else -> {
                    useCase.invoke(
                        weight = weight,
                        note = note,
                        timeStamp = timeStamp,
                        emoji = emoji
                    )

                    eventChannel.send(
                        Event.PopBackStack
                    )
                }
            }
        }
    }

    fun fetchWeightByDate(date: Date) {
        viewModelScope.launch(Dispatchers.IO) {
            val weight = repository.findWeightByDate(
                startOfDay = date.startOfDay(),
                endOfDay = date.endOfDay()
            )
            _uiState.update {
                it.copy(currentWeight = weight.lastOrNull())
            }
        }
    }

    data class UiState(var currentWeight: WeightUiModel? = null)

    sealed class Event {
        object PopBackStack : Event()
        data class Toast(@StringRes private val text: Int) : Event()
    }
}