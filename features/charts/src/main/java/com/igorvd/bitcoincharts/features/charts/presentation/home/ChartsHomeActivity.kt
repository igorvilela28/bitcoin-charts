package com.igorvd.bitcoincharts.features.charts.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.core.presentation.extensions.viewBinding
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartsHomeActivity : AppCompatActivity() {

    private val viewModel: ChartsHomeViewModel by viewModels()
    private val viewBinding: ActivityChartsHomeBinding by viewBinding(ActivityChartsHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel.launch { getHomeScreen() }

        viewBinding.button.setOnClickListener {
            val intent = BitcoinChartActivity.newIntent(this)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChartsHomeActivity::class.java)
        }
    }
}