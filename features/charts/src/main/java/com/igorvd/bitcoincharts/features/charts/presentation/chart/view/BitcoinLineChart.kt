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
        // labels and icons of the dataset are draw only if the number of entries of the dataSet is
        // less than the max visible value count
        setMaxVisibleValueCount(chart.entries.size + 1)
        // on the chart, there is two Y axis. For this one, we don't need the right one.
        axisRight.isEnabled = false
        description.isEnabled = false
        legend.isEnabled = false
        setBackgroundColor(Color.TRANSPARENT)
        isDoubleTapToZoomEnabled = false
        setPinchZoom(false)
        isDragEnabled = true
        setScaleEnabled(false)
        setNoDataText("")
        // this is used to set the distance from a tap that should activate the highlight for the entry
        maxHighlightDistance = CHART_MAX_HIGHLIGHT_DISTANCE
        isHighlightPerTapEnabled = true
        isHighlightPerDragEnabled = true
        // this disable an offset that was adding a padding for the chart at start
        minOffset = 0f
        extraBottomOffset = CHART_EXTRA_BOTTOM_OFFSET
    }

    private fun LineChart.setupXAxis(chart: Chart) = with(xAxis) {
        xAxis.setCenterAxisLabels(true)
        setDrawGridLines(false)
        position = XAxis.XAxisPosition.BOTTOM
        // creates an offset between the chart and the X labels
        yOffset = X_AXIS_Y_OFFSET
        // avoid the X labels clip outside of the screen
        setAvoidFirstLastClipping(true)
        axisMinimum = chart.entries.firstOrNull()?.x?.toFloat() ?: 0F
        axisMaximum = chart.entries.lastOrNull()?.x?.toFloat() ?: 0F
    }

    private fun LineChart.setupYAxis(chart: Chart) = with(axisLeft) {
        axisMinimum = 0F
        axisMaximum = chart.entries.lastOrNull()?.y?.toFloat() ?: 0F
        setDrawGridLines(false)
        setDrawAxisLine(true)
        setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        xOffset = Y_AXIS_X_OFFSET
        //textSize = AXIS_TEXT_SIZE
    }

    private fun setDataSet(chart: Chart) {
        val dataset = LineDataSet(mapEntries(chart.entries), "dataset").apply {
            setDrawValues(false)
            setDrawIcons(false)
            lineWidth = 1f
            setColor(context.getColorCompat(R.color.purple_500))
            this.mode = LineDataSet.Mode.CUBIC_BEZIER
            axisDependency = YAxis.AxisDependency.LEFT
            setDrawCircles(false)
            setDrawCircleHole(false)
            this.isHighlightEnabled = true
            setDrawHorizontalHighlightIndicator(false)
            setDrawVerticalHighlightIndicator(true)
            highLightColor = context.getColorCompat(R.color.purple_500)
        }

        val lineData = LineData(dataset)
        setData(lineData)
        notifyDataSetChanged()
    }

    private fun mapEntries(entries: List<ChartEntry>): List<Entry> {
        return entries.map { Entry(it.x.toFloat(), it.y.toFloat()) }
    }

    companion object {
        private const val CHART_EXTRA_BOTTOM_OFFSET = 16f
        private const val CHART_MAX_HIGHLIGHT_DISTANCE = 24f
        private const val X_AXIS_Y_OFFSET = 16f
        private const val Y_AXIS_X_OFFSET = 16f
        private const val AXIS_TEXT_SIZE = 14f
    }
}