package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorViewExceptionMapper
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetHomeStatsScreenUseCase
import com.igorvd.bitcoincharts.features.charts.presentation.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChartsHomeViewModel @Inject constructor(
    private val getHomeStatsScreenUseCase: GetHomeStatsScreenUseCase,
    private val errorViewExceptionMapper: ErrorViewExceptionMapper
) : ViewModel() {

    private val _homeStateFlow = MutableStateFlow<HomeState?>(null)
    val homeStateFlow: StateFlow<HomeState?> get() = _homeStateFlow

    suspend fun getHomeScreen() {
        try {
            _homeStateFlow.value = HomeState.Loading
            val stats = getHomeStatsScreenUseCase.get()
            _homeStateFlow.value = HomeState.ShowStats(stats)
        } catch (e: Exception) {
            _homeStateFlow.value =
                HomeState.ShowError(errorViewExceptionMapper.mapToErrorViewData(e))
        }
    }
}