package com.altyyev.calority.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.altyyev.calority.R
import com.altyyev.calority.databinding.FragmentHomeBinding
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.ui.home.adapter.ItemDecorator
import com.altyyev.calority.ui.home.adapter.WeightHistoryAdapter
import com.altyyev.calority.utils.viewBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val weightHistoryAdapter by lazy {
        WeightHistoryAdapter(::onClickWeight)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = weightHistoryAdapter
        recyclerView.addItemDecoration(ItemDecorator(requireContext()))
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(), DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect(::setUiState)
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) {
        weightHistoryAdapter.submitList(uiState.histories)
        setBarChart(uiState.histories)

    }


    private fun setBarChart(histories: List<WeightUiModel>) = with(binding) {

        val values = histories.reversed().mapIndexed { index, weight ->
            BarEntry(index.toFloat(), weight.weight?.toFloat() ?: 0f)
        }
        val chart = BarDataSet(values, "")
        chart.run {
            valueTextSize = 10f
            val dataSet: ArrayList<IBarDataSet> = ArrayList()
            dataSet.add(this)
            val data = BarData(dataSet)
            barChart.data = data
            barChart.invalidate()
            barChart.run {
                setDrawBorders(true)
                setGridBackgroundColor(R.color.Cream)
                setBackgroundColor(Color.WHITE);
                setDrawBorders(true)
                description.isEnabled = false
                setPinchZoom(false)
                color = Color.MAGENTA

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_weight -> {
                findNavController().navigate(R.id.action_homeFragment_to_addWeightFragment)
            }
        }
        return super.onOptionsItemSelected(item)

    }


    private fun onClickWeight(weight: WeightUiModel) {
    }
}

