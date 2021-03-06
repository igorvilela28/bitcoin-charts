package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart

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
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.core.presentation.extensions.getColorCompat
import com.igorvd.bitcoincharts.core.presentation.extensions.getDrawableCompat
import com.igorvd.bitcoincharts.core.presentation.extensions.getFontCompat
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricChart
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartEntry
import com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter.XAxisFormatter
import com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter.YAxisFormatter
import com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter.YAxisFormatterFactory

class BitcoinLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LineChart(context, attrs, defStyleAttr) {

    private val highlightEntries = mutableListOf<Entry>()
    private lateinit var yAxisFormatterFactory: YAxisFormatterFactory

    fun setup(dateTimeService: DateTimeService, yAxisFormatterFactory: YAxisFormatterFactory) {
        xAxis.valueFormatter = XAxisFormatter(dateTimeService)
        this.yAxisFormatterFactory = yAxisFormatterFactory
    }

    fun setChart(chart: BitcoinMetricChart) {
        setupChart(chart)
        setupXAxis()
        setupYAxis(chart)
        setDataSet(chart)
        invalidate()
    }

    private fun setupChart(chart: BitcoinMetricChart) {
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
        extraTopOffset = CHART_EXTRA_TOP_OFFSET
        extraBottomOffset = CHART_EXTRA_BOTTOM_OFFSET
        marker = CustomMarkerView(context)
    }

    private fun LineChart.setupXAxis() = with(xAxis) {
        setDrawGridLines(false)
        position = XAxis.XAxisPosition.BOTTOM
        yOffset = X_AXIS_Y_OFFSET
        setAvoidFirstLastClipping(true)
        setLabelCount(X_AXIS_LABEL_COUNT, true)
        textSize = LABEL_SIZE
        typeface = context.getFontCompat(R.font.nunito_semibold)
    }

    private fun LineChart.setupYAxis(chart: BitcoinMetricChart) = with(axisLeft) {
        setDrawGridLines(false)
        setDrawAxisLine(true)
        setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        xOffset = Y_AXIS_X_OFFSET
        valueFormatter = YAxisFormatter(chart.type, yAxisFormatterFactory)
        textSize = LABEL_SIZE
        typeface = context.getFontCompat(R.font.nunito_semibold)
    }

    private fun setDataSet(chart: BitcoinMetricChart) {
        val dataset = LineDataSet(mapEntries(chart.entries), "dataset").apply {
            setDrawValues(false)
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
            highLightColor = context.getColorCompat(R.color.grayScale400)
        }

        val lineData = LineData(dataset)
        setData(lineData)
    }

    private fun mapEntries(entries: List<MetricChartEntry>): List<Entry> {
        return entries.map { Entry(it.x.toFloat(), it.y.toFloat(), it.formattedEntry) }
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
        private const val CHART_EXTRA_BOTTOM_OFFSET = 16f
        private const val CHART_EXTRA_TOP_OFFSET = 4f
        private const val CHART_MAX_HIGHLIGHT_DISTANCE = 24f
        private const val LABEL_SIZE = 12f
        private const val X_AXIS_Y_OFFSET = 16f
        private const val X_AXIS_LABEL_COUNT = 4
        private const val Y_AXIS_X_OFFSET = 16f
        private const val DATA_SET_LINE_WIDTH = 1.0f
    }
}