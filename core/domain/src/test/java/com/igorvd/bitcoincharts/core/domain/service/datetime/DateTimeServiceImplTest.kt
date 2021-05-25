package com.igorvd.bitcoincharts.core.domain.service.datetime

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Locale

class DateTimeServiceImplTest {
    @MockK
    private lateinit var localeService: LocaleService

    private lateinit var dateTimeService: DateTimeServiceImpl

    private val timestamp = 1590192000L

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { localeService.getLocale() } returns Locale.US
        dateTimeService = DateTimeServiceImpl(localeService)
    }

    @Test
    fun `should parse timestamp into DatePattern MMM_yy`() {
        val formattedDate =
            dateTimeService.convertTimestampToDatePattern(timestamp, DatePattern.MMM_yy)
        assertEquals("May 20", formattedDate)
    }

    @Test
    fun `should parse timestamp into DatePattern MMM_dd_yyyy`() {
        val formattedDate =
            dateTimeService.convertTimestampToDatePattern(timestamp, DatePattern.MMM_dd_yyyy)
        assertEquals("May 22, 2020", formattedDate)
    }
}