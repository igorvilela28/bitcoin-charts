package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.core.view.isInvisible
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry

class BitcoinChartLayoutContainer(
    private val viewBinding: ActivityChartBinding,
    private val dateTimeService: DateTimeService
) {

    fun setup() = viewBinding.apply {

        lineChart.setup(dateTimeService)

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

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

    fun setScreenContent(bitcoinMetricScreen: BitcoinMetricScreen) = viewBinding.apply {
        tvTitle.text = bitcoinMetricScreen.title
        tvDescription.text = bitcoinMetricScreen.description
        lineChart.setChart(bitcoinMetricScreen.chart)
    }
}