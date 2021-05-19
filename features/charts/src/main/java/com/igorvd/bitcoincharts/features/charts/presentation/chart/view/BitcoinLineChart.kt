package com.igorvd.bitcoincharts.features.charts.presentation.chart.view

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart

class BitcoinLineChart(
    context: Context,
    attrs: AttributeSet? = null
) : LineChart(context, attrs) {

    fun setChart(chart: Chart) {

    }
}