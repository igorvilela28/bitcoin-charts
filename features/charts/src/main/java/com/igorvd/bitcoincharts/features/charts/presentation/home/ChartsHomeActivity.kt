package com.igorvd.bitcoincharts.features.charts.presentation.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartsHomeActivity : AppCompatActivity() {

    private val chartsHomeViewModel: ChartsHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts_home)

        findViewById<Button>(R.id.button).setOnClickListener {
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