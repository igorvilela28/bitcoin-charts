package com.igorvd.bitcoincharts.features.charts.presentation.home

import android.util.Log
import androidx.core.view.isVisible
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen

class ChartsHomeLayoutContainer(
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

        Log.d("Igor", data.toString())
    }
}