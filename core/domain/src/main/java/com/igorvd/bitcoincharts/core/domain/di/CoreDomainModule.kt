package com.igorvd.bitcoincharts.core.domain.di

import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatterImpl
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
    fun providesDateTimeService(dateTimeServiceImpl: DateTimeServiceImpl): DateTimeService {
        return dateTimeServiceImpl
    }
}