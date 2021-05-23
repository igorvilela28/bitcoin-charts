package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MetricScreenChartYMapperTest {

    @MockK
    private lateinit var currencyFormatter: CurrencyFormatter

    @MockK
    private lateinit var bitcoinFormatter: BitcoinFormatter

    @MockK
    private lateinit var numberFormatter: NumberFormatter

    private lateinit var mapper: MetricScreenChartYMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mapper = MetricScreenChartYMapper(currencyFormatter, bitcoinFormatter, numberFormatter)
    }

    @Test
    fun `should format Y value when chart type is MARKET_PRICE`() {

        val expectedFormattedValue = "$ 1,000,000.00"
        every { currencyFormatter.formatUSD(DUMMY_Y_VALUE) } returns expectedFormattedValue

        val formattedValue = mapper.formatYValue(DUMMY_Y_VALUE, ChartType.MARKET_PRICE)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) { currencyFormatter.formatUSD(DUMMY_Y_VALUE) }
        verify { listOf(bitcoinFormatter, numberFormatter) wasNot Called }
    }

    @Test
    fun `should format Y value when chart type is TOTAL_TRANSACTION_FEES`() {

        val expectedFormattedValue = "1,000,000.000 BTC"
        every {
            bitcoinFormatter.format(
                DUMMY_Y_VALUE,
                MetricScreenChartYMapper.TOTAL_TRANSACTION_FEES_FRACTION_DIGITS,
                true
            )
        } returns expectedFormattedValue

        val formattedValue = mapper.formatYValue(DUMMY_Y_VALUE, ChartType.TOTAL_TRANSACTION_FEES)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) {
            bitcoinFormatter.format(
                DUMMY_Y_VALUE,
                MetricScreenChartYMapper.TOTAL_TRANSACTION_FEES_FRACTION_DIGITS,
                true
            )
        }
        verify { listOf(currencyFormatter, numberFormatter) wasNot Called }
    }

    @Test
    fun `should format Y value when chart type is NUMBER_OF_TRANSACTIONS`() {

        val expectedFormattedValue = "1M"
        every { numberFormatter.formatCompact(DUMMY_Y_VALUE) } returns expectedFormattedValue

        val formattedValue = mapper.formatYValue(DUMMY_Y_VALUE, ChartType.NUMBER_OF_TRANSACTIONS)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) { numberFormatter.formatCompact(DUMMY_Y_VALUE) }
        verify { listOf(bitcoinFormatter, currencyFormatter) wasNot Called }
    }

    @Test
    fun `should format Y value when chart type is MINERS_REVENUE`() {

        val expectedFormattedValue = "1M"
        every { numberFormatter.formatCompact(DUMMY_Y_VALUE) } returns expectedFormattedValue

        val formattedValue = mapper.formatYValue(DUMMY_Y_VALUE, ChartType.MINERS_REVENUE)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) { numberFormatter.formatCompact(DUMMY_Y_VALUE) }
        verify { listOf(bitcoinFormatter, currencyFormatter) wasNot Called }
    }

    @Test
    fun `should format Y value when chart type is OUTPUT_VOLUME`() {

        val numberFormated = "1M"
        val expectedFormattedValue = "$numberFormated ${BitcoinFormatter.BITCOIN_UNIT}"
        every { numberFormatter.formatCompact(DUMMY_Y_VALUE) } returns numberFormated

        val formattedValue = mapper.formatYValue(DUMMY_Y_VALUE, ChartType.OUTPUT_VOLUME)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) { numberFormatter.formatCompact(DUMMY_Y_VALUE) }
        verify { listOf(bitcoinFormatter, currencyFormatter) wasNot Called }
    }

    @Test
    fun `should format Y value when chart type is ESTIMATED_TRANSACTION_VOLUME_BTC`() {

        val numberFormated = "1M"
        val expectedFormattedValue = "$numberFormated ${BitcoinFormatter.BITCOIN_UNIT}"
        every { numberFormatter.formatCompact(DUMMY_Y_VALUE) } returns numberFormated

        val formattedValue =
            mapper.formatYValue(DUMMY_Y_VALUE, ChartType.ESTIMATED_TRANSACTION_VOLUME_BTC)

        assertEquals(expectedFormattedValue, formattedValue)
        verify(exactly = 1) { numberFormatter.formatCompact(DUMMY_Y_VALUE) }
        verify { listOf(bitcoinFormatter, currencyFormatter) wasNot Called }
    }

    companion object {
        private const val DUMMY_Y_VALUE = 1_000_000.00
    }
}