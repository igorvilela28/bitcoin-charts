package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import com.igorvd.bitcoincharts.features.charts.presentation.home.adapter.StatsCategoryAdapter
import com.igorvd.bitcoincharts.features.charts.presentation.home.model.HomeState

class ChartsHomeLayoutContainer(
    private val activity: ChartsHomeActivity,
    private val viewBinding: ActivityChartsHomeBinding
) {

    fun setState(state: HomeState) = viewBinding.apply {
        state.setViewsVisibility()
        when (state) {
            is HomeState.ShowStats -> state.set()
            is HomeState.ShowError -> state.set()
        }
    }

    fun HomeState.setViewsVisibility() = viewBinding.apply {
        pbLoading.isVisible = this@setViewsVisibility is HomeState.Loading
        nsvRoot.isVisible = this@setViewsVisibility is HomeState.ShowStats
        errorView.isVisible = this@setViewsVisibility is HomeState.ShowError
    }

    private fun HomeState.ShowStats.set() = viewBinding.apply {
        tvTitle.text = homeScreen.title
        tvDescription.text = homeScreen.description
        rvStatsCategories.apply {
            layoutManager = LinearLayoutManager(rvStatsCategories.context)
            adapter = StatsCategoryAdapter(homeScreen.statsCategories) {
                val intent = BitcoinChartActivity.newIntent(activity, it)
                activity.startActivity(intent)
            }
        }
    }

    private fun HomeState.ShowError.set() = viewBinding.apply {
        errorView.setData(errorViewData) {
            activity.viewModel.launch { getHomeScreen() }
        }
    }
}