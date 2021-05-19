package com.igorvd.bitcoincharts.features.charts.presentation.chart

import android.util.Log
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart

class BitcoinChartLayoutContainer(private val viewBinding: ActivityChartBinding) {

    fun setChart(chart: Chart) {
        viewBinding.tvTitle.text = chart.name
        viewBinding.tvDescription.text = chart.description
        viewBinding.lineChart.setChart(chart)
    }
}