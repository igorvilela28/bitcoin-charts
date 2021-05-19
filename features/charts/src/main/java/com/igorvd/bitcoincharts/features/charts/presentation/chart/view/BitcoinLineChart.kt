package com.igorvd.bitcoincharts.features.charts.presentation.chart.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.igorvd.bitcoincharts.core.presentation.extensions.getColorCompat
import com.igorvd.bitcoincharts.core.presentation.extensions.getDrawableCompat
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartEntry

class BitcoinLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LineChart(context, attrs, defStyleAttr) {

    private val highlightEntries = mutableListOf<Entry>()

    fun setChart(chart: Chart) {
        setupChart(chart)
        setupXAxis(chart)
        setupYAxis(chart)
        setDataSet(chart)
        invalidate()
    }

    private fun setupChart(chart: Chart) {
        clear()
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
        extraTopOffset = CHART_EXTRA_OFFSET
        extraBottomOffset = CHART_EXTRA_OFFSET
        marker = CustomMarkerView(context)
    }

    private fun LineChart.setupXAxis(chart: Chart) = with(xAxis) {
        setDrawGridLines(false)
        position = XAxis.XAxisPosition.BOTTOM
        yOffset = X_AXIS_Y_OFFSET
        setAvoidFirstLastClipping(true)
        setLabelCount(X_AXIS_LABEL_COUNT, true)
        axisMinimum = chart.entries.firstOrNull()?.x?.toFloat() ?: 0F
        axisMaximum = chart.entries.lastOrNull()?.x?.toFloat() ?: 0F
        valueFormatter = XAxisFormatter()
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
            this.setDrawIcons(true)
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

    override fun highlightValue(high: Highlight?, callListener: Boolean) {

        clearHighlights()

        data.dataSets.forEach {
            high ?: return
            it.highlightEntriesForXValue(high.x)
        }

        super.highlightValue(high, callListener)
    }

    private fun clearHighlights() {
        highlightEntries.forEach {
            it.icon = null
        }
        highlightEntries.clear()
    }

    private fun ILineDataSet.highlightEntriesForXValue(x: Float) {
        val entries = getEntriesForXValue(x)
        entries.forEach {
            it.icon = context.getDrawableCompat(R.drawable.ic_highlight_circle)
        }
        highlightEntries.addAll(entries)
    }

    companion object {
        private const val CHART_EXTRA_OFFSET = 16f
        private const val CHART_MAX_HIGHLIGHT_DISTANCE = 24f
        private const val X_AXIS_Y_OFFSET = 16f
        private const val X_AXIS_LABEL_COUNT = 4
        private const val Y_AXIS_X_OFFSET = 16f
        private const val DATA_SET_LINE_WIDTH = 1.5f
    }
}