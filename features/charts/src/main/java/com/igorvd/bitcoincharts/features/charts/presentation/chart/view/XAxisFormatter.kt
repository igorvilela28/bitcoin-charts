package com.igorvd.bitcoincharts.features.charts.presentation.chart.view

import com.github.mikephil.charting.formatter.ValueFormatter
import com.igorvd.bitcoincharts.core.domain.service.datetime.DatePattern
import com.igorvd.bitcoincharts.core.domain.timeStampToDatePattern

class XAxisFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return timeStampToDatePattern(value.toLong(), DatePattern.MMM_yy.pattern)
    }
}