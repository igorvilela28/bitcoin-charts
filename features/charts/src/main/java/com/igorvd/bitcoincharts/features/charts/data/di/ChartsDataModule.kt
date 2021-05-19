package com.igorvd.bitcoincharts.features.charts.data.di

import com.igorvd.bitcoincharts.core.data.network.ApiClientBuilder
import com.igorvd.bitcoincharts.features.charts.data.BitcoinChartRepositoryImpl
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object ChartsDataModule {

    @Provides
    @ViewModelScoped
    fun providesBlockchainApi(): BlockchainApi {
        return ApiClientBuilder.createService(BlockchainApi::class.java, BlockchainApi.BASE_URL)
    }

    @Provides
    @ViewModelScoped
    fun providesBitcoinChartRepository(
        bitcoinChartRepositoryImpl: BitcoinChartRepositoryImpl
    ): BitcoinChartRepository {
        return bitcoinChartRepositoryImpl
    }
}