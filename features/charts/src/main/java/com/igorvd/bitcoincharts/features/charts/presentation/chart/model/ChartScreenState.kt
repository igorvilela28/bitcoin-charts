package com.igorvd.bitcoincharts.features.charts.presentation.chart.model

import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorView
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen

sealed class ChartScreenState {
    object Loading : ChartScreenState()
    data class ShowMetricScreen(val bitcoinMetricScreen: BitcoinMetricScreen) : ChartScreenState()
    data class ShowError(val errorViewData: ErrorView.ErrorViewData) : ChartScreenState()
}