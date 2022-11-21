package com.altyyev.calority.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altyyev.calority.data.WeightRepository
import com.altyyev.calority.domain.uimodel.WeightUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeightRepository
) : ViewModel() {

    init {
        getAllHistories()
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

     fun getAllHistories() {
         viewModelScope.launch(Dispatchers.IO) {
             repository.getAllHistory().collectLatest { list ->
                 _uiState.update {
                     it.copy(
                         histories = list
                     )
                 }
             }
         }
     }


    data class UiState(var histories: List<WeightUiModel> = emptyList())
}