package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import com.igorvd.bitcoincharts.features.charts.presentation.home.adapter.StatsCategoryAdapter

class ChartsHomeLayoutContainer(
    private val activity: ChartsHomeActivity,
    private val viewBinding: ActivityChartsHomeBinding
) {

    fun setLoading(isLoading: Boolean) = viewBinding.apply {
        pbLoading.isVisible = isLoading
        if (isLoading) {
            nsvRoot.isVisible = false
        }
    }

    fun setStatsHomeScreen(data: BitcoinStatsHomeScreen) = viewBinding.apply {
        nsvRoot.isVisible = true

        tvTitle.text = data.title
        tvDescription.text = data.description
        rvStatsCategories.apply {
            layoutManager = LinearLayoutManager(rvStatsCategories.context)
            adapter = StatsCategoryAdapter(data.statsCategories) {
                val intent = BitcoinChartActivity.newIntent(activity, it)
                activity.startActivity(intent)
            }
        }
    }
}