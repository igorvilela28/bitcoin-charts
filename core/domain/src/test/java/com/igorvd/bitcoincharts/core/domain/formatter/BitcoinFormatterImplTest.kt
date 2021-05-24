package com.igorvd.bitcoincharts.core.domain.formatter

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Locale

class BitcoinFormatterImplTest {

    @MockK
    private lateinit var localeService: LocaleService

    private lateinit var formatter: BitcoinFormatterImpl

    private val value = 2.05

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { localeService.getLocale() } returns Locale.ENGLISH
        formatter = BitcoinFormatterImpl(localeService)
    }

    @Test
    fun `should format value with BTC unit when fraction digit is 3`() {
        val formattedValue = formatter.format(value, 3, true)
        assertEquals("2.050 BTC", formattedValue)
    }

    @Test
    fun `should format value without BTC unit when fraction digit is 8`() {
        val formattedValue = formatter.format(value, 8, false)
        assertEquals("2.05000000", formattedValue)
    }


}