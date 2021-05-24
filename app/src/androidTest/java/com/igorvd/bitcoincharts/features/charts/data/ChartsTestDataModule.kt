package com.igorvd.bitcoincharts.features.charts.data

import com.igorvd.bitcoincharts.core.data.network.ApiClientBuilder
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object ChartsTestDataModule {

    @Provides
    @ViewModelScoped
    fun providesBlockchainApi(): BlockchainApi {
        return ApiClientBuilder.createService(BlockchainApi::class.java, "http://localhost:8080/")
    }

    @Provides
    @ViewModelScoped
    fun providesBitcoinChartRepository(
        bitcoinChartRepositoryImpl: BitcoinChartRepositoryImpl
    ): BitcoinChartRepository {
        return bitcoinChartRepositoryImpl
    }
}