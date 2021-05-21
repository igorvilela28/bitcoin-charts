package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetMetricScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BitcoinChartViewModel @Inject constructor(
    private val getMetricScreenUseCase: GetMetricScreenUseCase
) : ViewModel() {

    val chartStateFlow = MutableStateFlow<BitcoinMetricScreen?>(null)

    suspend fun getChart() {
        val response = getMetricScreenUseCase.get(ChartType.TOTAL_TRANSACTION_FEES)
        chartStateFlow.value = response
    }
}