package com.altyyev.calority.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.altyyev.calority.data.WeightRepository
import com.altyyev.calority.domain.uimodel.WeightUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: WeightRepository
) : ViewModel() {
    val weightHistories: LiveData<List<WeightUiModel>> = repository.getAllHistory()
}