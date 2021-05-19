package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.igorvd.bitcoincharts.core.domain.timeStampToDatePattern
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart

class BitcoinChartLayoutContainer(private val viewBinding: ActivityChartBinding) {

    fun setup() = viewBinding.apply {

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(e: Entry, h: Highlight) {
                clLineChartHighlightInfo.isVisible = true
                tvXValue.text = timeStampToDatePattern(e.x.toLong())
                tvYValue.text = e.y.toString()
            }

            override fun onNothingSelected() {
                clLineChartHighlightInfo.isInvisible = true
            }
        })
    }

    fun setChart(chart: Chart) = viewBinding.apply {
        tvTitle.text = chart.name
        tvDescription.text = chart.description
        lineChart.setChart(chart)
    }
}