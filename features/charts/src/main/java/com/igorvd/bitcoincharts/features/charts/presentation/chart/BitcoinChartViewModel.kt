package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetChartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BitcoinChartViewModel @Inject constructor(
    private val getChartUseCase: GetChartUseCase
) : ViewModel() {

    val chartStateFlow = MutableStateFlow<Chart?>(null)

    suspend fun getChart() {
        val response = getChartUseCase.get(ChartType.MARKET_PRICE)
        chartStateFlow.value = response
    }
}