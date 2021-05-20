package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter

import com.github.mikephil.charting.formatter.ValueFormatter
import com.igorvd.bitcoincharts.core.domain.service.datetime.DatePattern
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Inject

class XAxisFormatter(private val dateTimeService: DateTimeService) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return dateTimeService.convertTimestampToDatePattern(value.toLong(), DatePattern.MMM_yy)
    }
}