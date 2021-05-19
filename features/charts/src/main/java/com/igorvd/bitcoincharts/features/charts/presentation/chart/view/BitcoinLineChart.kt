package com.igorvd.bitcoincharts.features.charts.presentation.chart.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.igorvd.bitcoincharts.core.domain.timeStampToDatePattern
import com.igorvd.bitcoincharts.core.presentation.extensions.getColorCompat
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartEntry

class BitcoinLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LineChart(context, attrs, defStyleAttr) {

    fun setChart(chart: Chart) {
        setupChart(chart)
        setupXAxis(chart)
        setupYAxis(chart)
        setDataSet(chart)
        invalidate()
    }

    private fun setupChart(chart: Chart) {
        this.clear()
        setMaxVisibleValueCount(chart.entries.size + 1)
        axisRight.isEnabled = false
        description.isEnabled = false
        legend.isEnabled = false
        setBackgroundColor(Color.TRANSPARENT)
        isDoubleTapToZoomEnabled = false
        setPinchZoom(false)
        isDragEnabled = true
        setScaleEnabled(false)
        setNoDataText("")
        maxHighlightDistance = CHART_MAX_HIGHLIGHT_DISTANCE
        isHighlightPerTapEnabled = true
        isHighlightPerDragEnabled = true
        minOffset = 0f
        extraBottomOffset = CHART_EXTRA_BOTTOM_OFFSET
        setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(e: Entry, h: Highlight) {
                Log.d("Igor", "${e.x.toLong()} - ${e.y} - ${timeStampToDatePattern(e.x.toLong())}")
            }

            override fun onNothingSelected() {
            }
        })
    }

    private fun LineChart.setupXAxis(chart: Chart) = with(xAxis) {
        xAxis.setCenterAxisLabels(true)
        setDrawGridLines(false)
        position = XAxis.XAxisPosition.BOTTOM
        yOffset = X_AXIS_Y_OFFSET
        setAvoidFirstLastClipping(true)
        setLabelCount(X_AXIS_LABEL_COUNT, true)
        axisMinimum = chart.entries.firstOrNull()?.x?.toFloat() ?: 0F
        axisMaximum = chart.entries.lastOrNull()?.x?.toFloat() ?: 0F
    }

    private fun LineChart.setupYAxis(chart: Chart) = with(axisLeft) {
        axisMinimum = 0F
        axisMaximum = chart.entries.maxByOrNull { it.y }?.y?.toFloat() ?: 0F
        setDrawGridLines(false)
        setDrawAxisLine(true)
        setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        xOffset = Y_AXIS_X_OFFSET
    }

    private fun setDataSet(chart: Chart) {
        val dataset = LineDataSet(mapEntries(chart.entries), "dataset").apply {
            setDrawValues(false)
            setDrawIcons(false)
            lineWidth = DATA_SET_LINE_WIDTH
            setColor(context.getColorCompat(R.color.purple_500))
            this.mode = LineDataSet.Mode.CUBIC_BEZIER
            axisDependency = YAxis.AxisDependency.LEFT
            setDrawCircles(false)
            setDrawCircleHole(false)
            this.isHighlightEnabled = true
            setDrawHorizontalHighlightIndicator(false)
            setDrawVerticalHighlightIndicator(true)
            highLightColor = context.getColorCompat(R.color.black)
        }

        val lineData = LineData(dataset)
        setData(lineData)
    }

    private fun mapEntries(entries: List<ChartEntry>): List<Entry> {
        return entries.map { Entry(it.x.toFloat(), it.y.toFloat()) }
    }

    companion object {
        private const val CHART_EXTRA_BOTTOM_OFFSET = 16f
        private const val CHART_MAX_HIGHLIGHT_DISTANCE = 24f
        private const val X_AXIS_Y_OFFSET = 16f
        private const val X_AXIS_LABEL_COUNT = 4
        private const val Y_AXIS_X_OFFSET = 16f
        private const val DATA_SET_LINE_WIDTH = 1.5f
    }
}