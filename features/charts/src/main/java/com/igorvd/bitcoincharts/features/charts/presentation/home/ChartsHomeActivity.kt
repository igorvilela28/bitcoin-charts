package com.igorvd.bitcoincharts.features.charts.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.core.presentation.extensions.viewBinding
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartsHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChartsHomeActivity : AppCompatActivity() {

    private val viewModel: ChartsHomeViewModel by viewModels()
    private val viewBinding: ActivityChartsHomeBinding by viewBinding(ActivityChartsHomeBinding::inflate)
    private val layoutContainer by lazy {
        ChartsHomeLayoutContainer(viewBinding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupObservers()
        viewModel.launch { getHomeScreen() }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.apply {
                    loadingStateFlow.filterNotNull().collect {
                        layoutContainer.setLoading(it)
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeStateFlow.filterNotNull().collect {
                    layoutContainer.setStatsHomeScreen(it)
                }
            }
        }


    }

companion object {
    fun newIntent(context: Context): Intent {
        return Intent(context, ChartsHomeActivity::class.java)
    }
}
}