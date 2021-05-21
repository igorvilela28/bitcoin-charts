package com.igorvd.bitcoincharts.core.domain.di

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatterImpl
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatterImpl
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatterImpl
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreDomainModule {

    @Provides
    @Singleton
    fun providesCurrencyFormatter(currencyFormatterImpl: CurrencyFormatterImpl): CurrencyFormatter {
        return currencyFormatterImpl
    }

    @Provides
    @Singleton
    fun providesBitcoinFormatter(bitcoinFormatterImpl: BitcoinFormatterImpl): BitcoinFormatter {
        return bitcoinFormatterImpl
    }

    @Provides
    @Singleton
    fun providesNumberFormatter(numberFormatterImpl: NumberFormatterImpl): NumberFormatter {
        return numberFormatterImpl
    }

    @Provides
    @Singleton
    fun providesDateTimeService(dateTimeServiceImpl: DateTimeServiceImpl): DateTimeService {
        return dateTimeServiceImpl
    }
}