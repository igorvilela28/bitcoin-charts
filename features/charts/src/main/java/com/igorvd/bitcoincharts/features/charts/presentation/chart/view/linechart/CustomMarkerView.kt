package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.utils.MPPointF
import com.igorvd.bitcoincharts.features.charts.R

class CustomMarkerView(context: Context) :
    MarkerView(context, R.layout.view_line_chart_custom_marker) {

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        val offset = MPPointF()
        val width = width.toFloat()
        offset.x = 0 - width / 2
        offset.y = 0 - posY
        return offset
    }
}