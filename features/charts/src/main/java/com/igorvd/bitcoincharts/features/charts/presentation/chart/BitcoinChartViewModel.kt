package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorViewExceptionMapper
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetMetricScreenUseCase
import com.igorvd.bitcoincharts.features.charts.presentation.chart.model.ChartScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BitcoinChartViewModel @Inject constructor(
    private val getMetricScreenUseCase: GetMetricScreenUseCase,
    private val errorViewExceptionMapper: ErrorViewExceptionMapper
) : ViewModel() {

    private val _chartScreenStateFlow = MutableStateFlow<ChartScreenState?>(null)
    val chartScreenStateFlow: StateFlow<ChartScreenState?> get() = _chartScreenStateFlow

    suspend fun getChartData(chartType: ChartType) {
        try {
            _chartScreenStateFlow.value = ChartScreenState.Loading
            val metricScreenData = getMetricScreenUseCase.get(chartType)
            _chartScreenStateFlow.value = ChartScreenState.ShowMetricScreen(metricScreenData)
        } catch (e: Exception) {
            _chartScreenStateFlow.value =
                ChartScreenState.ShowError(errorViewExceptionMapper.mapToErrorViewData(e))
        }
    }
}