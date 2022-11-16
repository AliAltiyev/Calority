package com.altyyev.calority.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.altyyev.calority.R
import com.altyyev.calority.databinding.FragmentHomeBinding
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.ui.home.adapter.WeightHistoryAdapter
import com.altyyev.calority.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val weightHistoryAdapter by lazy {
        WeightHistoryAdapter(::onClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun onClick(weightUiModel: WeightUiModel) {

    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = weightHistoryAdapter
    }

    private fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect(
                ::setUiState
            )
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) {
        weightHistoryAdapter.submitList(uiState.histories)
    }


}