package com.igorvd.bitcoincharts.features.charts.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartsHomeViewModel @Inject constructor() : ViewModel() {

    fun getChartsHome() {
        Log.d("Igor", "it works!")
    }

}