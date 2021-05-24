package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry
import com.igorvd.bitcoincharts.features.charts.presentation.chart.model.ChartScreenState

class BitcoinChartLayoutContainer(
    private val activity: BitcoinChartActivity,
    private val viewBinding: ActivityChartBinding
) {

    fun setup() = viewBinding.apply {
        toolbar.ivBackButton.setOnClickListener {
            activity.finish()
        }
        lineChart.apply {
            setup(activity.dateTimeService, activity.yAxisFormatterFactory)
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

                override fun onValueSelected(e: Entry, h: Highlight) {
                    val formattedEntry = e.data as? MetricChartFormattedEntry
                    clLineChartHighlightInfo.isInvisible = formattedEntry == null
                    tvXValue.text = formattedEntry?.formattedX
                    tvYValue.text = formattedEntry?.formattedY
                }

                override fun onNothingSelected() {
                    clLineChartHighlightInfo.isInvisible = true
                }
            })
        }
    }

    fun setScreenContent(state: ChartScreenState) = viewBinding.apply {
        state.setViewsVisibility()
        when (state) {
            is ChartScreenState.ShowMetricScreen -> state.set()
            is ChartScreenState.ShowError -> state.set()
        }
    }

    fun ChartScreenState.setViewsVisibility() = viewBinding.apply {
        pbLoading.isVisible = this@setViewsVisibility is ChartScreenState.Loading
        clContent.isVisible = this@setViewsVisibility is ChartScreenState.ShowMetricScreen
        errorView.isVisible = this@setViewsVisibility is ChartScreenState.ShowError
    }

    private fun ChartScreenState.ShowMetricScreen.set() = viewBinding.apply {
        toolbar.tvToolbarTitle.text = bitcoinMetricScreen.title
        tvChartDescription.text = bitcoinMetricScreen.description
        lineChart.setChart(bitcoinMetricScreen.chart)
    }

    private fun ChartScreenState.ShowError.set() = viewBinding.apply {
        errorView.setData(errorViewData) {
            activity.viewModel.launch { getChartData(activity.chartType) }
        }
    }
}