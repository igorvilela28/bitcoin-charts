package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.service.datetime.DatePattern
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartEntryResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricChart
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartEntry
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry
import com.igorvd.bitcoincharts.features.charts.testutil.bitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.testutil.chartResponse
import com.igorvd.bitcoincharts.features.charts.testutil.entryResponse
import com.igorvd.bitcoincharts.features.charts.testutil.metricChartEntry
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MetricScreenMapperTest {

    @MockK
    private lateinit var dateTimeService: DateTimeService

    @MockK
    private lateinit var yMapper: MetricScreenChartYMapper

    private lateinit var mapper: MetricScreenMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = MetricScreenMapper(dateTimeService, yMapper)
    }

    @Test
    fun `should map ChartResponse to BitcoinMetricScreen`() {

        every {
            dateTimeService.convertTimestampToDatePattern(
                1L,
                DatePattern.MMM_dd_yyyy
            )
        } returns metricChartEntry.formattedEntry.formattedX

        every {
            yMapper.formatYValue(
                entryResponse.y,
                ChartType.MARKET_PRICE
            )
        } returns metricChartEntry.formattedEntry.formattedY

        val metricScreen = mapper.mapFromChart(chartResponse, ChartType.MARKET_PRICE)

        assertEquals(bitcoinMetricScreen, metricScreen)

        verifyOrder {
            dateTimeService.convertTimestampToDatePattern(
                1L,
                DatePattern.MMM_dd_yyyy
            )
            yMapper.formatYValue(
                entryResponse.y,
                ChartType.MARKET_PRICE
            )
        }
    }
}