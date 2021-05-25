package com.igorvd.bitcoincharts.core.domain.formatter

import android.icu.text.CompactDecimalFormat
import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verifyOrder
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Locale

class NumberFormatterImplTest {

    @MockK
    private lateinit var localeService: LocaleService

    @RelaxedMockK
    private lateinit var compactDecimalFormat: CompactDecimalFormat

    private lateinit var formatter: NumberFormatterImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(CompactDecimalFormat::class)
        every {
            CompactDecimalFormat.getInstance(
                Locale.US,
                CompactDecimalFormat.CompactStyle.SHORT
            )
        } returns compactDecimalFormat
        every { localeService.getLocale() } returns Locale.US
        formatter = NumberFormatterImpl(localeService)
    }

    @After
    fun teardown() {
        unmockkStatic(CompactDecimalFormat::class)
    }

    @Test
    fun `should format number value`() {
        val formattedValue = formatter.format(1000000)
        assertEquals("1,000,000", formattedValue)
    }

    @Test
    fun `should format compact with 6 significant digits when number is below 1M`() {
        val value = 123_456.78
        every { compactDecimalFormat.format(value) } returns "123.456K"
        val formattedValue = formatter.formatCompact(value)
        assertEquals("123.456K", formattedValue)
        verifyOrder {
            compactDecimalFormat.setMaximumSignificantDigits(6)
            compactDecimalFormat.setMinimumSignificantDigits(6)
            compactDecimalFormat.format(value)
        }
    }

    @Test
    fun `should format compact with 1 significant digits when number is below 1M`() {
        val value = 200_000.00
        every { compactDecimalFormat.format(value) } returns "200K"
        val formattedValue = formatter.formatCompact(value)
        assertEquals("200K", formattedValue)
        verifyOrder {
            compactDecimalFormat.setMaximumSignificantDigits(1)
            compactDecimalFormat.setMinimumSignificantDigits(1)
            compactDecimalFormat.format(value)
        }
    }

    @Test
    fun `should format compact with 4 significant digits when number is above 1M`() {
        val value = 1_234_567.89
        every { compactDecimalFormat.format(value) } returns "123.4M"
        val formattedValue = formatter.formatCompact(value)
        assertEquals("123.4M", formattedValue)
        verifyOrder {
            compactDecimalFormat.setMaximumSignificantDigits(4)
            compactDecimalFormat.setMinimumSignificantDigits(4)
            compactDecimalFormat.format(value)
        }
    }

    @Test
    fun `should format compact with 1 significant digits when number is above 1M`() {
        val value = 2_000_000.00
        every { compactDecimalFormat.format(value) } returns "2M"
        val formattedValue = formatter.formatCompact(value)
        assertEquals("2M", formattedValue)
        verifyOrder {
            compactDecimalFormat.setMaximumSignificantDigits(1)
            compactDecimalFormat.setMinimumSignificantDigits(1)
            compactDecimalFormat.format(value)
        }
    }
}