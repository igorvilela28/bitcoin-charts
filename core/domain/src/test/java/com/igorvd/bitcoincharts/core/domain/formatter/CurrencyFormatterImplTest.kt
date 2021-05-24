package com.igorvd.bitcoincharts.core.domain.formatter

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Locale

class CurrencyFormatterImplTest {

    @MockK
    private lateinit var localeService: LocaleService

    private lateinit var formatter: CurrencyFormatterImpl

    private val value = 2000.05

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { localeService.getLocale() } returns Locale.ENGLISH
        formatter = CurrencyFormatterImpl(localeService)
    }

    @Test
    fun `should format value into USD currency`() {
        val formattedValue = formatter.formatUSD(value)
        assertEquals("$2,000.05", formattedValue)
    }
}