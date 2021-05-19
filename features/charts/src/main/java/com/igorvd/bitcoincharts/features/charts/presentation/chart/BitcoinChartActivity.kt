package com.igorvd.bitcoincharts.features.charts.presentation.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.core.presentation.extensions.viewBinding
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BitcoinChartActivity : AppCompatActivity() {

    private val viewModel: BitcoinChartViewModel by viewModels()
    private val viewBinding: ActivityChartBinding by viewBinding(ActivityChartBinding::inflate)
    private val layoutContainer by lazy {
        BitcoinChartLayoutContainer(viewBinding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        setupObservers()
        viewModel.launch { getChart() }
    }

    private fun setupObservers() = lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.chartStateFlow.filterNotNull().collect {
                layoutContainer.setChart(it)
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BitcoinChartActivity::class.java)
        }
    }
}