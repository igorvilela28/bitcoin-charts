package com.igorvd.bitcoincharts.features.charts.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.features.charts.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartsHomeActivity : AppCompatActivity() {

    private val chartsHomeViewModel: ChartsHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts_home)
        chartsHomeViewModel.launch { getChartsHome() }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChartsHomeActivity::class.java)
        }
    }
}