package com.igorvd.bitcoincharts.features.charts.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.bitcoincharts.core.presentation.extensions.collect
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.core.presentation.extensions.viewBinding
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class ChartsHomeActivity : AppCompatActivity() {

    val viewModel: ChartsHomeViewModel by viewModels()
    private val viewBinding: ActivityChartsHomeBinding by viewBinding(ActivityChartsHomeBinding::inflate)
    private val layoutContainer by lazy {
        ChartsHomeLayoutContainer(this, viewBinding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupObservers()
        viewModel.launch { getHomeScreen() }
    }

    private fun setupObservers() = viewModel.apply {
        collect(loadingStateFlow.filterNotNull()) {
            layoutContainer.setLoading(it)
        }
        collect(homeStateFlow.filterNotNull()) { state ->
            layoutContainer.setState(state)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChartsHomeActivity::class.java)
        }
    }
}