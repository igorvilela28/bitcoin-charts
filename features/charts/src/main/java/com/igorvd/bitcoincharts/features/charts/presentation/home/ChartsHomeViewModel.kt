package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetHomeStatsScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChartsHomeViewModel @Inject constructor(
    private val getHomeStatsScreenUseCase: GetHomeStatsScreenUseCase
) : ViewModel() {

    private val _loadingStateFlow = MutableStateFlow<Boolean?>(null)
    val loadingStateFlow: StateFlow<Boolean?> get() = _loadingStateFlow

    private val _homeStateFlow = MutableStateFlow<BitcoinStatsHomeScreen?>(null)
    val homeStateFlow: StateFlow<BitcoinStatsHomeScreen?> get() = _homeStateFlow

    suspend fun getHomeScreen() {
        _loadingStateFlow.value = true
        val result = getHomeStatsScreenUseCase.get()
        _homeStateFlow.value = result
        _loadingStateFlow.value = false
    }
}